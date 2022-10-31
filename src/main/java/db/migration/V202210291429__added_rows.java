package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class V202210291429__added_rows extends BaseJavaMigration {

    @Override
    public void migrate(Context context) throws Exception {

        try (PreparedStatement pstmt = context.getConnection().prepareStatement("INSERT INTO book(title, writer, pages) VALUES (?,?,?)")) {
            Random random = new Random();

            for (int i = 1; i <= 1000000; i++) {
                pstmt.setString(1, "Book" + i);
                pstmt.setString(2, "Writer" + i);
                pstmt.setInt(3, random.nextInt(1000) + 1);
                pstmt.addBatch();
                if (i % 100 == 0) { // <-- this will add 100 rows at a time.
                    pstmt.executeBatch();
                    System.out.println("==================== "+ i +" ===============");
                }
            }
            pstmt.executeBatch();
        } catch (
                SQLException e) {
            throw new SQLException("ERROR: INSERT RANDOM VALUE" + e);
        }
    }
}

