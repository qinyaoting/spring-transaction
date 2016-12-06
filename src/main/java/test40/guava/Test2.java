package test40.guava;

import schemacrawler.schema.*;
import schemacrawler.schemacrawler.*;
import schemacrawler.utility.SchemaCrawlerUtility;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

import static us.fatehi.commandlineparser.CommandLineUtility.applyApplicationLogLevel;
import static us.fatehi.commandlineparser.CommandLineUtility.logSystemProperties;

/**
 * Created by chin on 12/4/16.
 */
public class Test2 {

    public static void main(String[] args) throws SQLException, SchemaCrawlerException {
        applyApplicationLogLevel(Level.ALL);
        // Log system properties and classpath
        logSystemProperties();

        // Create the options
        final SchemaCrawlerOptions options = new SchemaCrawlerOptions();
        // Set what details are required in the schema - this affects the
        // time taken to crawl the schema
        options.setSchemaInfoLevel(SchemaInfoLevelBuilder.standard());
        options.setRoutineInclusionRule(new ExcludeAll());
        //options.setSchemaInclusionRule(new RegularExpressionInclusionRule("PUBLIC.BOOKS"));

        // Get the schema definition
        final Catalog catalog = SchemaCrawlerUtility.getCatalog(getConnection(),
                options);

        for (final Schema schema: catalog.getSchemas())
        {
            System.out.println(schema);
            for (final Table table: catalog.getTables(schema))
            {
                System.out.print("o--> " + table);
                if (table instanceof View)
                {
                    System.out.println(" (VIEW)");
                }
                else
                {
                    System.out.println();
                }

                for (final Column column: table.getColumns())
                {
                    System.out.println("     o--> " + column + " ("
                            + column.getColumnDataType() + ")");
                }
            }
        }
    }

    private static Connection getConnection()
            throws SchemaCrawlerException, SQLException
    {
        //final DataSource dataSource = new DatabaseConnectionOptions("jdbc:hsqldb:hsql://localhost:9001/schemacrawler");//jdbc:oracle:thin:@192.168.20.201:1521:orcl
        final DataSource dataSource = new DatabaseConnectionOptions("jdbc:oracle:thin:@13.52.165.21:1521/snstga");//
        return dataSource.getConnection("root", "rootname");
    }
}
