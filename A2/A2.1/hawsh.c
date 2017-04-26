/*
   HAW-Shell
   <Version: 1.0>
   <Author: Anil Ersin Kaya, Ali Calis>
   <Compilen mit: gcc -Wall -std=gnu99 hawsh.c -o hawsh>
   Erstellt: <06.04.2017>
 */
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <unistd.h>
#include <string.h>
#include <sys/stat.h> //sys muss im Include Order sein
#include <sys/types.h>
#include <sys/wait.h>

#define KGRN  "\x1B[32m"  // GREEN
#define KRED  "\x1B[31m"  // RED
#define KNRM  "\x1B[0m"   // NORMAL
#define FILEMODE 0755     // FILEMODE FOR CHDIR
#define BUFFER 1024         // BUFFER FOR CHAR ARRAY

// Konstanten
const char *befehle[4] = {"quit", "version", "/[path]", "help"};
const char *beschreibung[4] = {
        "Close the HAW-Shell","Show the version information",
        "Switch to the desired path or create a new Folder","Shows this message"
};
const size_t bufsize = BUFFER;

// Variablen
char currentdir[BUFFER];
char userinput[BUFFER];
char input[BUFFER];
char username[BUFFER];
int i = 0;
int len;
int returnvalue;
char output[BUFFER];
char background_output[BUFFER];
int background = 0;
FILE *in;


int switch_create_dir(){
        if (chdir(userinput) != 0) {
                if(mkdir(userinput,FILEMODE) != 0) {
                        perror("Couldn't create Directory\n");
                }
                else{
                        printf("Directory created: %s \n", userinput);
                }
                if(chdir(userinput) != 0) {
                        perror("Couldn't switch in the created Directory\n");
                }
        }
        else {
                if(chdir(userinput) != 0) {
                        perror("Couldn't switch the Directory\n");
                }
        }
        return 0;
}

int help() {
        printf("Built-In Commands\t\tFunction\n");
        printf("----------------\t\t--------\n");
        for (size_t i = 0; i < (sizeof(befehle) / sizeof(befehle[0])); i++) {
                printf("%s\t\t\t\t%s\n", befehle[i],beschreibung[i]);
        }
        return 0;
}

int version() {
        printf("\n");
        printf("\n[HAW-Shell v1.0]");
        printf("\nAuthor: Anil Ersin Kaya");
        printf("\nAuthor: Ali Calis");
        printf("\n");
        return 0;
}

int control() {
        while(true) {
                if(getcwd(currentdir, sizeof(currentdir))==NULL) {
                        perror("Couldn't get Current Directory\n");
                }
                if(getlogin_r(username,bufsize) != 0) {
                        perror("Couldn't get Username\n");
                }
                printf("%s%s%s@%s:",KGRN, username, KNRM, currentdir);
                if (background == 1) {
                        printf("\n");
                        while(fgets(background_output,sizeof(background_output),in)!= NULL) {
                                printf("%s",background_output);
                        }
                }
                background = 0;
                //pclose(in); // Bei Linux Ausklammern sonst Schrott
                scanf("%s", userinput);
                len = strlen(userinput);
                if ((strcmp(userinput,"help") == 0) || (strcmp(userinput,"h")== 0)) {
                        help();
                }
                else if ((strcmp(userinput,"version")== 0) || (strcmp(userinput,"v")== 0)) {
                        version();
                }
                else if ((strcmp(userinput,"quit")== 0) || (strcmp(userinput,"q")== 0)) {
                        printf("Bye-Bye!\n");
                        break;
                }
                else if (strncmp(userinput,"/",1) == 0) {
                        switch_create_dir();
                }
                else if (strcmp(&userinput[len-1],"&") == 0) {
                        memset(&input,0,sizeof(input));
                        strncpy(input,userinput,strlen(userinput)-1);
                        if(!(in = popen(input,"r"))) {
                                printf("%sCouldn't create Background Process and/or Pipe\n%s", KRED,KNRM);
                        }
                        background = 1;
                }
                else {
                        int pid = fork();
                        if (pid == 0) {
                                //printf("New Process created (PID %d)\n", getpid());  //move to LOG-File
                                returnvalue = execlp(userinput,getenv("HOME"),NULL);
                                if (returnvalue == -1) {
                                        printf("%sNot a valid command!\n%s", KRED,KNRM);
                                }
                                exit(0);
                        }
                        else if (pid > 0) {
                                waitpid(pid,NULL,0);
                                control();
                        }
                        else {
                                perror("Couldn't create Child-Process\n");
                        }
                        return 0;
                }
        }
        return 0;
}

int main(){
        control();
        return 0;
}
