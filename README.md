# miniterminal
CASO PRÁCTICO A - MiniTerminal & MiniFileManager

Implementa un programa que funcione como una pequeña terminal Linux con algunos comandos
(simplificados) que permitan al usuario realizar distintas operaciones de gestión de archivos. Los
comandos que el usuario podrá ejecutar son:

● pwd: Muestra cual es la carpeta actual.

● cd: Cambia la carpeta actual a ‘DIR’. Con .. cambia a la carpeta superior.
  
● ls: Muestra la lista de directorios y archivos de la carpeta actual (primero directorios, luego
archivos, ambos ordenados alfabéticamente).
  
● ll: Como ls pero muestra también el tamaño y la fecha de última modificación.
● mkdir <DIR>: Crea el directorio ‘DIR’ en la carpeta actual.
  
● rm <FILE>: Borra ‘FILE’. Si es una carpeta, borrará primero sus archivos y luego la carpeta. Si
tiene subcarpetas, las dejará intactas y mostrará un aviso al usuario.
  
● mv <FILE1> <FILE2>: Mueve o renombra ‘FILE1’ a ‘FILE2’.
  
● help: Muestra una breve ayuda con los comandos disponibles.
  
● exit: Termina el programa.
  
Clase MiniTerminal: Clase principal (con función main) que se encargará de interactuar con el
usuario e interpretar los comandos (qué comando se pide, argumentos, etc.). Utilizará la segunda
clase para realizar las operaciones de gestión de archivos. Manejará todas las posibles excepciones.
Clase MiniFileManager: Tendrá los atributos y métodos que necesites para realizar las distintas
operaciones relacionadas con la gestión de archivos. Necesitarás al menos un método por cada
operación. Se anzará una excepción si se produce un error o la operación solicitada no es posible.
Algunos ejemplos que podrías implementar:
  
● String getPWD(): Devuelve una cadena de texto con la carpeta actual.
  
● boolean changeDir(String dir): Cambia la carpeta actual a dir. Devuelve ‘true’ si fué posible.
  
● void printList(boolean info): Muestra una lista con los directorios y archivos de la carpeta
actual. Si info es ‘true’ mostrará también su tamaño y fecha de modificación.
  
● etc.

