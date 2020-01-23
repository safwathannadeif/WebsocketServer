package com.shd.server.logging ;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
 

    public class LogFormat extends Formatter
    {

       // private static final MessageFormat messageFormat = new MessageFormat("[{3,date,hh:mm:ss} {2} {0} {5}]{4} \n");
        private static final MessageFormat messageFormat = new MessageFormat("[ {1} {3} {2} {0} {5}]{4} \n");

        public LogFormat()
        {
            super();
        }

        @Override
        public String format(LogRecord record)
        {
            Object[] arguments = new Object[6];
            arguments[0] = record.getSourceClassName() ;
            //arguments[0] = record.getLoggerName();
            arguments[1] = record.getLevel();
            arguments[2] = Thread.currentThread().getName();
            arguments[3] = formatDate2(record.getMillis());
            arguments[4] = record.getMessage();
            arguments[5] = record.getSourceMethodName();
            return messageFormat.format(arguments);
        }
        private String formatDate2(long millisecs) {

            SimpleDateFormat date_format = new SimpleDateFormat("dd/MMM/yyyy HH:mm:sss");

            Date resultdate = new Date(millisecs);

            return date_format.format(resultdate);

    }
    }