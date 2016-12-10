import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import java.nio.charset.Charset

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO
import static ch.qos.logback.classic.Level.TRACE
import static ch.qos.logback.classic.Level.ERROR
import ch.qos.logback.classic.jul.LevelChangePropagator

scan()
def lcp = new LevelChangePropagator()
lcp.context = context
lcp.resetJUL = true
context.addListener(lcp)

appender("consoleAppender", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    charset = Charset.forName("UTF-8")
    pattern = "%d %-4relative [%thread] %-5level %logger{35} - %msg%n"
  }
}
appender("FILE", RollingFileAppender) {
  file = "logs/metrics.log"
  rollingPolicy(TimeBasedRollingPolicy) {
    fileNamePattern = "logs/metrics%d{yyyy-MM-dd}.%i.log"
    timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
      maxFileSize = "10MB"
    }
    maxHistory = 30
  }
  encoder(PatternLayoutEncoder) {
    charset = Charset.forName("UTF-8")
    pattern = "%d %-4relative [%thread] %-5level %logger{35} - %msg%n"
  }
}
root(INFO, ["consoleAppender", "FILE"])