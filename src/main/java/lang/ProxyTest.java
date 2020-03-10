package lang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyTest {
    public static void main(String[] args) {
        Foo f = (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(), new Class<?>[]{Foo.class}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(Arrays.toString(args));
                return null;
            }
        });

        f.say("hello");
    }
}

interface Foo{
    void say(String message);
}
