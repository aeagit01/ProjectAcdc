package com.javarush.khmelov.tools;

import com.javarush.khmelov.entity.QuestElement;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import static java.nio.charset.StandardCharsets.*;
@SuppressWarnings("unused")
public class Keys {

    public static final Path WEB_INF = Paths.get(
                                URI.create(
                                Objects.requireNonNull(QuestElement.class
                                                       .getResource("/")
                                ).toString()))
                                 .getParent();

    public static final String QUESTS_FILE = "/quest.txt";
    public static final String QUESTIONS_FILE = "/questions.txt";
    public static final String RESPONSES_FILE = "/responses.txt";
    public static final String FINISH_FILE = "/finish.txt";
    public static final String QUESTS_DATA_FILE = "/questdata.txt";
    public static final String HELP_FILE = "/help.txt";


    public static final Charset CODE_UTF8 = UTF_8;
    public static final Long ELEMENT_FIRST = 1L;
    public static final Long ELEMENT_LAST = 2L;
    public static final Long ELEMENT_ORDINARY = 0L;
    public static final Integer OUT_OF_INDEX = -1;

    public static final String WEBINF = "WEB-INF";
    public static final String IMAGE = "image";

    public static final String HOME = "home";
    public static final String POST = "post";
    public static final String GET = "get";
    public static final String CREATE = "create";
    public static final String UPDATE = "update";
    public static final String OK = "ok";
    public static final String CANCEL = "cancel";
    public static final String QUESTION = "question";
    public static final String RESPONSE = "response";
    public static final String EMPTYSTR = "";

    public static final String ROLES = "roles";
    public static final String ROLE = "role";
    public static final String USER = "user";
    public static final String USERS = "users";
    public static final String QUESTS = "quests";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";


    public static final String COMMAND_EDIT = "edit";
    public static final String COMMAND_START = "start";
    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_SELECT_RESPONSE = "selectresponse";
    public static final String COMMAND_NEXT = "next";
    public static final String COMMAND_PREV = "prev";
    public static final String COMMAND_SELECT_QUESTION = "selectquestion";
    public static final String COMMAND_SELECT_NEXT = "selectnext";


    public static final String PARAMETR_ID = "id";
    public static final String PARAMETR_FINISH = "f";
    public static final String PARAMETR_QUESTION = "q";
    public static final String PARAMETR_RESPONSE = "r";
    public static final String COMMAND_SELECTQUEST = "select-quest";

    public static final String JSP_VAL_CHECKFLAG = "questionchk";
    public static final String JSP_VAL_DIRECT = "direct";
    public static final String JSP_VAL_FIRSTELEMENT = "firstchk";
    public static final String JSP_VAL_CHECKEDFINISHMESSAGE = "finmessagechk";
    public static final String JSP_VAL_CHECKEDQUESTION = "chkquestions";
    public static final String JSP_VAL_RESPONSECHK = "responsechk";
    public static final String JSP_VAL_RESPONSELIST = "responselist";
    public static final String JSP_VAL_RESPONSES = "responses";
    public static final String JSP_VAL_QUESTION = "questions";
    public static final String JSP_VAL_QUEST = "quest";
    public static final String JSP_VAL_BUTTONNAME = "buttonname";
    public static final String JSP_VAL_QUESTIMAGE = "buttonname";
    public static final String JSP_VAL_RESPONSEIMAGE = "responseimage";
    public static final String JSP_VAL_QUESTIONIMAGE = "questionimage";
    public static final String JSP_VAL_CHECKEDFM = "chkfin";
    public static final String JSP_VAL_CHKQ = "chkq";
    public static final String JSP_VAL_FINMESSAGES = "finmessages";

    public static final String NOIMAGE = "noimage.png";

    public static final String BN_RESPONSE = "Ответить";
    public static final String BN_FINISH = "Завершить";

    public static final String CLASSPATH = "com.javarush.khmelov.cmd.";


}
