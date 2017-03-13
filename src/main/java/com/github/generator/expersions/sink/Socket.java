package com.github.generator.expersions.sink;

import com.github.generator.expersions.Expression;

import java.io.OutputStreamWriter;

/**
 * @author Mostafa Asgari
 * @since 3/12/17
 */
public class Socket extends Expression {

    private Expression expression;
    private String host;
    private int port;

    public Socket(Expression expression , String address) {
        this.expression = expression;

        String[] split = address.split(":");
        if( split.length != 2 ){
            throw new IllegalArgumentException("address");
        }
        this.host = split[0];
        this.port = Integer.valueOf(split[1]);
    }

    public Expression getExpression() {
        return expression;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String generate() throws Exception {

        java.net.Socket socket = null;
        OutputStreamWriter writer = null;
        String generatedData = expression.generate();
        try {
            socket = new java.net.Socket(host, port);

            writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write(generatedData);
            writer.flush();
        }
        finally {
            if(writer!=null)
                writer.close();

            if(socket!=null)
                socket.close();
        }

        return generatedData;
    }
}
