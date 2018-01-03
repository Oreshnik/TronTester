import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class TronProcess {

    private Process process;
    private PrintStream out;
    private Scanner in;
    private BufferedReader error;

    public TronProcess(Process process) {
        this.process = process;
        out = new PrintStream(process.getOutputStream());
        in = new Scanner(process.getInputStream());
        error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
    }

    public void destroy() throws IOException {
        out.close();
        in.close();
        error.close();
        process.destroy();
    }

    public void flushOut() {
        out.flush();
    }

    public void print(String string) {
        out.println(string);
    }

    public String readLine() {
        return in.nextLine();
    }

    public void clearErrorStream() throws IOException {
        while (error.ready()) {
            error.readLine();
        }
    }
}
