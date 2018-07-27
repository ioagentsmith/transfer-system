package za.co.ioagentsmith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Version {

    private static final Logger LOGGER = LoggerFactory.getLogger(Version.class);

    private static final String ARTIFACT = "${project.parent.artifactId}";
    private static final String VERSION = "${project.parent.version}";
    private static final String GROUP_ID = "${project.parent.groupId}";
    private static final String TIMESTAMP = "${timestamp}";
    private static final String TIMESTAMP_FORMAT = "${maven.build.timestamp.format}";

    public static String getArtifact() {
        return ARTIFACT;
    }

    public static String getVersion() {
        return VERSION;
    }

    public static String getGroupId() {
        return GROUP_ID;
    }

    public static String getTimestamp() {
        return getTimestampAtCAT();
    }

    public static String getTimestampFormat() {
        return TIMESTAMP_FORMAT;
    }

    public static String getTimestampAtCAT() {
        String versionDate = TIMESTAMP;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Version.getTimestampFormat());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("CAT"));
        try {
            return simpleDateFormat.parse(versionDate).toString();
        } catch (ParseException pe) {
            LOGGER.warn("Unable to convert date <" + versionDate + "> to CAT");
        }
        return versionDate;
    }
}