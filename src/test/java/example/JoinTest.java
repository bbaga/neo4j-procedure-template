package example;

import org.junit.Rule;
import org.junit.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Config;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.harness.junit.rule.Neo4jRule;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JoinTest {

    @Rule
    public Neo4jRule neo4j = new Neo4jRule().withFunction(Join.class);

    @Test
    public void shouldJoinStrings() throws Throwable
    {
        try (Driver driver = GraphDatabase.driver(neo4j.boltURI(), Config.builder().withoutEncryption().build())) {
            Session session = driver.session();
            String result = session.run("RETURN example.join(['Hello', 'World']) AS result").single().get("result").asString();
            assertEquals(result, "Hello,World");
        }
    }
}