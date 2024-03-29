module xiaohe.common.base {

    exports com.xiaohe66.common.dto;
    exports com.xiaohe66.common.reflect;
    exports com.xiaohe66.common.util;
    exports com.xiaohe66.common.util.concurrent;
    exports com.xiaohe66.common.util.ex;
    exports com.xiaohe66.common.util.gson;
    exports com.xiaohe66.common.util.jackson;
    exports com.xiaohe66.common.util.time;

    opens com.xiaohe66.common.dto;
    opens com.xiaohe66.common.util.jackson;

    requires static lombok;
    requires transitive org.slf4j;

    requires transitive com.fasterxml.jackson.databind;
    requires transitive com.fasterxml.jackson.datatype.jsr310;

    requires transitive org.apache.commons.lang3;

    requires static com.google.gson;
    requires static com.google.common;
    requires static com.fasterxml.jackson.dataformat.xml;
    requires static org.apache.commons.io;
    requires static org.apache.commons.codec;

    requires static html2pdf;
    requires static java.management;
    requires static com.auth0.jwt;


}