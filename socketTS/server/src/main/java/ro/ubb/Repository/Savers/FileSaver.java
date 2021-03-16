package ro.ubb.Repository.Savers;

import ro.ubb.Model.Exceptions.SaverException;
import ro.ubb.Model.BaseEntity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @param <K> ID type
 * @param <T> entity type
 */
public class FileSaver<K, T extends BaseEntity<K>> extends AbstractSaver<K, T>{
    private BufferedWriter writer;

    public FileSaver(String savePath) {
        super(savePath);
    }

    public void saveToFile(Map<K, T> iRepository) throws IOException {
        writer = new BufferedWriter(new FileWriter(super.filePath));
        iRepository.forEach((key, value) -> {
            try {
                writer.write(value.csvFileFormat());
            } catch (IOException ioException) {
                throw new SaverException("Error at writing the csv file!");
            }
        });
        writer.close();
    }
}
