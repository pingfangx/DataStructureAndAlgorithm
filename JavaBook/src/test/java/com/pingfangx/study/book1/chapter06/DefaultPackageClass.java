import org.junit.Assert;

/**
 * @author pingfangx
 * @date 2019/10/18
 */
class DefaultPackageClass {
    public static void main(String[] args) {
        System.out.println("main");
        DefaultPackageClass defaultPackageTest = new DefaultPackageClass();
        Class clazz = defaultPackageTest.getClass();
        Package aPackage = clazz.getPackage();
        Assert.assertNull(aPackage);
    }
}
