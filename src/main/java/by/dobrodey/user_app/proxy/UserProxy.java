package by.dobrodey.user_app.proxy;

import by.dobrodey.user_app.model.User;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class UserProxy {

    public User contextInitialized() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(User.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                return ((String) proxy.invokeSuper(obj, args)).toUpperCase();
            } else {
                return proxy.invokeSuper(obj, args);
            }
        });
        return (User) enhancer.create();
    }
}
