package proyecto1.AbstractFactory;

public interface Factory<T> {
    T create(String type);
}
