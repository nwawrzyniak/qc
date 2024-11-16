# qc (quick-clipboard)
A lightweight CLI tool written in Java which lets the user load any predefined content into the clipboard.

## Download
The newest version of qc can always be found on the ["Releases" tab on GitHub](https://github.com/nwawrzyniak/qc/releases).

## Recommended setup
1. Install the JRE
2. Extract ```qc-version.zip``` into any directory (e.g. ```~/UserSoftware/qc```).
3. Add the directory to your PATH.
4. On Linux or macOS you may want to rename ```qc.sh``` to ```qc```.

## Usage
1. Place a text file containing a string to load (a "secret") inside ```~/.qc/secrets``` (you can open this directory with ```qc browse```).
2. Use ```qc [fileName]``` to load it.
3. Use ```qc help``` for a list of all commands.
