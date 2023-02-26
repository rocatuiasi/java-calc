package UnitTests;

import main.ExpressionTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static main.ExpressionTree.*;
class ExpressionTreeTest {
    @ParameterizedTest
    @CsvSource(value = {
            "2 * ( 4 - 5 / 6 ) / 456:0.013888888888888888",
            "2 * 3 + 5:11",
            "10 * 10:100",
            "2 + ( 450 * 3 - 3 ) / 3:451",
            "1 + 2 * ( 3 + 4 + 5 ) * 3 / 4 - ( 8 * 1 / 2 ):15",
            "4 - 8 + 5:1",
            "2 / 5 * 3:1.2000000000000002"
    }, delimiter = ':')
    void CheckInfixExpression(String expression, String expected) {
        var exp = new ExpressionTree(expression);
        Assertions.assertEquals(Double.parseDouble(expected), exp.EvaluateTree(exp.head));
    }
}