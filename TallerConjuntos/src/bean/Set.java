package bean;

@FunctionalInterface
public interface Set<O> {
    java.util.Set<O> realizar(java.util.Set<O> s1, java.util.Set<O> s2);
}
