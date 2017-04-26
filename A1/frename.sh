#!/bin/bash 
# <Das Shell script hängt für alle im aktuellen Verzeichnis befindenden Datein den String an.>
# <Anil Ersin Kaya> 
# <06.04.2017>

# ------------------------------------------------------------
# This function shows the help text for this bash script
usage() { 
echo "./frename [STRING]
Platziere an den Ort wo [STRING] steht einfach
den text hin der am ende angefügt werden soll.
"
}

# ---------------------- main --------------------------------
# check parameters 
#for i in .* *; do
#filenamen=${i%.*}
#exten=${i##*.}
#mv $i "${filenamen}$1.${exten}"
done

for i in *.*; do
mv $i "$i$1"
done
# ---------------------- end ---------------------------------
