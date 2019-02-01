package cumt.innovative.training.project.basketballappointment.utils;

import cumt.innovative.training.project.basketballappointment.logger.Logger;
import cumt.innovative.training.project.basketballappointment.model.Court;
import cumt.innovative.training.project.basketballappointment.model.Room;
import cumt.innovative.training.project.basketballappointment.model.User;
import cumt.innovative.training.project.basketballappointment.utils.db.TableCheckHelper;
import cumt.innovative.training.project.basketballappointment.utils.db.TableCreateHelper;
import cumt.innovative.training.project.basketballappointment.utils.db.TableInsertHelper;
import cumt.innovative.training.project.basketballappointment.utils.db.TableQueryHelper;
import sun.net.www.content.image.png;

import java.util.List;

public class MainUtil {
    public static void preHandleForServerRun() {
        ConfigurationUtil.loadConfigurationFromFile();
        checkTables(User.class, Room.class, Court.class);
        insertTestDataForUser();
//      insertTestDataForRoom();
    }

    private static void insertTestDataForUser() {
        User testUser1 = new User();
        testUser1.setAbility("1|3|4|2|6");
        testUser1.setAge(26);
        testUser1.setUserName("Neko");
        testUser1.setPassword("1234567");
        testUser1.setGender("Male");
        testUser1.setImage("profile-img-1.png");
        boolean nekoExist = exists(User.class, new FilterByObject() {
            public boolean existCondition(Object arg) {
                return ((User) arg).getUserName().equals("Neko");
            }
        });
        if (!nekoExist) {
            insertTestData(testUser1);
        }

        User testUser2 = new User();
        testUser2.setAbility("4|3|4|5|6");
        testUser2.setAge(28);
        testUser2.setUserName("Bird");
        testUser2.setPassword("1234567");
        testUser2.setGender("Male");
        testUser2.setImage("profile-img-2.png");
        boolean birdExist = exists(User.class, new FilterByObject() {
            public boolean existCondition(Object arg) {
                return ((User) arg).getUserName().equals("Bird");
            }
        });
        if (!birdExist) {
            insertTestData(testUser2);
        }

        User testUser3 = new User();
        testUser3.setAbility("2|2|2|2|6");
        testUser3.setAge(18);
        testUser3.setUserName("Cow");
        testUser3.setPassword("1234567");
        testUser3.setGender("Male");
        testUser3.setImage("profile-img-3.png");
        boolean cowExist = exists(User.class, new FilterByObject() {
            public boolean existCondition(Object arg) {
                return ((User) arg).getUserName().equals("Cow");
            }
        });
        if (!cowExist) {
            insertTestData(testUser3);
        }

        User testUser4 = new User();
        testUser4.setAbility("1|5|4|2|4");
        testUser4.setAge(24);
        testUser4.setUserName("Fish");
        testUser4.setPassword("1234567");
        testUser4.setGender("Male");
        testUser4.setImage("profile-img-4.png");
        boolean fishExist = exists(User.class, new FilterByObject() {
            public boolean existCondition(Object arg) {
                return ((User) arg).getUserName().equals("Fish");
            }
        });
        if (!fishExist) {
            insertTestData(testUser4);
        }

        User testUser5 = new User();
        testUser5.setAbility("4|3|4|4|4");
        testUser5.setAge(26);
        testUser5.setUserName("Lion");
        testUser5.setPassword("1234567");
        testUser5.setGender("Male");
        testUser5.setImage("profile-img-5.png");
        boolean lionExist = exists(User.class, new FilterByObject() {
            public boolean existCondition(Object arg) {
                return ((User) arg).getUserName().equals("Lion");
            }
        });
        if (!lionExist) {
            insertTestData(testUser5);
        }

        User testUser6 = new User();
        testUser6.setAbility("1|3|4|2|6");
        testUser6.setAge(26);
        testUser6.setUserName("Dog");
        testUser6.setPassword("1234567");
        testUser6.setGender("Male");
        testUser6.setImage("profile-img-6.png");
        boolean dogExist = exists(User.class, new FilterByObject() {
            public boolean existCondition(Object arg) {
                return ((User) arg).getUserName().equals("Dog");
            }
        });
        if (!dogExist) {
            insertTestData(testUser6);
        }

        User testUser7 = new User();
        testUser7.setAbility("1|3|4|2|6");
        testUser7.setAge(26);
        testUser7.setUserName("Rabbit");
        testUser7.setPassword("1234567");
        testUser7.setGender("Male");
        testUser7.setImage("profile-img-7.png");
        boolean rabbitExist = exists(User.class, new FilterByObject() {
            public boolean existCondition(Object arg) {
                return ((User) arg).getUserName().equals("Rabbit");
            }
        });
        if (!rabbitExist) {
            insertTestData(testUser7);
        }
    }

    private static void insertTestDataForRoom() {
        Room testRoom1 = new Room();
        testRoom1.setType(0);
        testRoom1.setCreator(1);
        testRoom1.setAppointmentTime("{from:{hour:9,minute:0},to:{hour:18,minute:0}}");
        testRoom1.setName("Nekos Room");
        testRoom1.setMembers("1|2|3");
        boolean nekoSRoomExists = exists(Room.class, new FilterByObject() {
            public boolean existCondition(Object arg) {
                return ((Room)arg).getName().equals("Nekos Room");
            }
        });
        if(!nekoSRoomExists) {
            insertTestData(testRoom1);
        }
    }

    private static void insertTestDataForCourt() {
        Court testCourt1 = new Court();
        testCourt1.setName("Nanhu Stadium");
        testCourt1.setX(100.00);
        testCourt1.setY(100.00);
        testCourt1.setPics("Nanhu Stadium 1.png|Nanhu Stadium 2.png");
        testCourt1.setCourtNumber(3);
        boolean testCourt1Exists = exists(Court.class, new FilterByObject() {
            public boolean existCondition(Object arg) {
                return ((Court)arg).getName().equals("Nanhu Stadium");
            }
        });
        if(!testCourt1Exists) {
            insertTestData(testCourt1);
        }
    }

    public static void checkTables(Class<?>... classes) {
        boolean checkAllPass = true;
        for (Class<?> cls : classes) {
            CheckResult result = TableCheckHelper.checkTable(cls);
            int logLevel = result == CheckResult.CheckPass ? Logger.INFO : (result == CheckResult.TableNotExist ? Logger.WARNING : Logger.DANGER);
            Logger.log(new Throwable(), logLevel, cls.getSimpleName() + ", " + result);
            if (result == CheckResult.TableNotExist) {
                TableCreateHelper.createTable(cls);
            } else if (result == CheckResult.TableExistButTypeMismatch){
                checkAllPass = false;
            }
        }
        if(!checkAllPass) {
            throw new RuntimeException("Not all tables checked pass, exit...");
        }
    }

    public static void insertTestData(Object... objects) {
        for (Object object : objects) {
            try {
                TableInsertHelper.insert(object);
            } catch (RuntimeException ex) {
                Logger.log(new Throwable(), Logger.DANGER, ex.getMessage());
            }
        }
    }

    public static <T> boolean exists(Class<T> cls, FilterByObject filter) {
        List<T> data = null;
        try {
            data = TableQueryHelper.query(cls);
        } catch (RuntimeException ex) {
            Logger.log(new Throwable(), Logger.DANGER, ex.getMessage());
        }
        if(data == null) {
            return false;
        }
        for (Object object : data) {
            if (filter.existCondition(object)) {
                return true;
            }
        }
        return false;
    }
}

interface FilterByObject {
    public boolean existCondition(Object arg);
}