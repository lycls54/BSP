#include <stdio.h>
#include <fcntl.h> // creat
#include <string.h> // strlen
#include <unistd.h> // for close
int main (void) {
    char MyName[30];
    
    printf("Name der neuen Datei: ");
    
    fgets(MyName, sizeof(MyName), stdin);
    
    // remove new line
    MyName[strlen(MyName) - 1] = 0;
    
    // file descriptor
    int fd = creat(MyName, S_IRWXU);
    if (fd < 0) {
   	 printf("Es ist ein Fehler aufgetreten!\n");
   	 return 1;
    } else {
   	 close(fd);
    }
    
    printf("Die Datei %s wurde erfolgreich angelegt!\n", MyName);
    
    return 0;
}
