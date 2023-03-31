package java20migration;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Java20MigrationApplication {

    public static void main(String[] args) throws SQLException {
        Server.main(
                // tcpリスナを有効化
                "-tcp",
                // 存在しないDBへの接続を試みたら作る
                "-ifNotExists"
        );
        SpringApplication.run(Java20MigrationApplication.class, args);
    }


}
