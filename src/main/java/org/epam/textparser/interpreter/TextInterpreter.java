package org.epam.textparser.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TextInterpreter {
    private static final String SCRIPT_ENGINE_NAME = "JavaScript";

    private static Logger logger = LogManager.getLogger(TextInterpreter.class);

    private TextInterpreter() {
    }

    public static String convertExpression(String expression) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName(SCRIPT_ENGINE_NAME);
        String resultExpression;
        try {
            resultExpression = engine.eval(expression).toString();
            logger.info("Successful convert expression: " + expression);
        } catch (ScriptException exp) {
            resultExpression = expression;
            logger.warn("Exception during convert expression:" + expression);
        }
        return resultExpression;
    }
}
