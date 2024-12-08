import com.sun.jdi.connect.Connector;

import java.nio.file.Path;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.*;
import javax.swing.JFileChooser;

import static java.nio.file.StandardOpenOption.CREATE;


public class FileScan {
    private static final Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile = null;
        ArrayList Lines = new ArrayList<String>();
        try {


            if(args.length > 0){
                String FileNamestr = args[0];
                String WorkingDir = System.getProperty("user.dir");
                selectedFile = new File(WorkingDir + File.separator + FileNamestr);
                if(!selectedFile.exists() || !selectedFile.isFile()){
                    System.out.println("File not found or the file is invalid " + selectedFile.getAbsolutePath());
                }
                else{

                    Summary(selectedFile);
                }

            }
            else{
                File workingDirectory = new File(System.getProperty("user.dir"));
                chooser.setCurrentDirectory(workingDirectory);

                if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                   selectedFile = chooser.getSelectedFile();
                   System.out.println(workingDirectory.getAbsolutePath());
                   Summary(selectedFile);
                }
                else  // User closed the chooser without selecting a file
                {
                    System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
                }


            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }






    }
    private static void Summary(File Filename) throws FileNotFoundException, IOException {
        String rec = "";
        int words = 0;
        int chars = 0;
        Path FileToRead = Filename.toPath();
        InputStream in =
                new BufferedInputStream(Files.newInputStream(FileToRead, CREATE));
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(in));

        int line = 0;
        while (reader.ready()) {
            rec = reader.readLine();
            System.out.printf("\nLine %4d %-60s ", line, rec);
            words += rec.split("\\s+").length;
            chars += rec.length();
            line++;
        }
        reader.close(); // must close the file to seal it and flush buffer
        System.out.println("\n\nData file read!");
        System.out.println(" \n Summary Report: \n File: " + Filename.getName() + "\n Lines: " + line + "\nWords: " + words + "\nChars: " + chars);

    }
}

