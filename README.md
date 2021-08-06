# quick-clipboard
A lightweight CLI tool written in Java which lets the user load any predefined content into the clipboard.

## Download
The newest version of qc can always be found on the ["Releases" tab on GitHub](https://github.com/nwawrzyniak/quick-clipboard/releases).

## Usage
Instead of calling qc via the command ```java -jar "path/to/qc.jar" "secret_file"``` you can place the attached script file to any directory within your PATH. If you move only the script and not the .jar file aswell, you should specify the new path in the script file.

If you want to load the contents of (e.g.) the file "ssh-key" in your ```.qc``` directory, just call ```qc ssh-key``` (or ```java -jar qc.jar ssh-key```) from the "run" dialog or from the command line. You can also specify a file by its absolute path instead.
