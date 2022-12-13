import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import execution.interpreters.Interpreter;
import fileio.Input;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        // args[0]
        String inputFilename = args[0];
        if (inputFilename.contains("basic_7.json"))
            solveInput(inputFilename, Test.OUT_FILE);
    }

    public static void solveInput(final String inputFilename, final String outputFilename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(inputFilename), Input.class);

        // Entrypoint
        Interpreter interpreter = new Interpreter(inputData);
        ArrayNode output = interpreter.runActions();

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(outputFilename), output);
        objectWriter.writeValue(new File(outputFilename + ".txt"), output);
    }
}
