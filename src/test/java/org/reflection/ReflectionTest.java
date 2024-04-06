    package org.reflection;

    import org.junit.jupiter.api.Test;
    import org.reflection.annotation.Controller;
    import org.reflection.annotation.Service;
    import org.reflection.model.User;
    import org.reflections.Reflections;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    import java.lang.annotation.Annotation;
    import java.util.Arrays;
    import java.util.HashSet;
    import java.util.List;
    import java.util.Set;
    import java.util.stream.Collectors;

    public class ReflectionTest {
        private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

        @Test
        void controllerScan() {
            Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));
            logger.debug("beans : [{}]", beans);

        }

        @Test
        void showClass() {
            Class<User> clazz = User.class;
            logger.debug(clazz.getName());
            logger.debug("User all decleared fields: [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
            logger.debug("User all decleared constructors: [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
            logger.debug("User all decleared methds:  [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
        }

        @Test
        void load() throws ClassNotFoundException {
            //1
            Class<User> clazz = User.class;
            //2
            User user = new User("serer", "홍길동");
            Class<? extends User> clazz2 = user.getClass();
            //3
            Class<?> clazz3 = Class.forName("org.reflection.model.User");

            logger.debug("clazz : [{}]" , clazz);
        }

        private Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
            Reflections reflections = new Reflections("org.reflection");

            Set<Class<?>> beans = new HashSet<>();
            annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));
            return beans;


        }
    }
