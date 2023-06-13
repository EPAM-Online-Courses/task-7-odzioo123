package efs.task.reflection;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

public class ClassInspector {


  /**
   * Metoda powinna wyszukać we wszystkich zadeklarowanych przez klasę polach te które oznaczone
   * są adnotacją podaną jako drugi parametr wywołania tej metody. Wynik powinien zawierać tylko
   * unikalne nazwy pól (bez powtórzeń).
   *
   * @param type       klasa (typ) poddawana analizie
   * @param annotation szukana adnotacja
   * @return lista zawierająca tylko unikalne nazwy pól oznaczonych adnotacją
   */
  public static Collection<String> getAnnotatedFields(final Class<?> type,
      final Class<? extends Annotation> annotation) {
    List<String> annotations_fields =  new ArrayList<>();
    Field[] fields = type.getDeclaredFields();

    for(Field field : fields)
    {
      if(field.isAnnotationPresent(annotation))
      {
        annotations_fields.add(field.getName());
      }
    }

    HashSet<String> annotations_fields_set = new HashSet<>(annotations_fields);
    List<String> annotations_fields2 = new ArrayList<>(annotations_fields_set);
    //TODO usuń zawartość tej metody i umieść tutaj swoje rozwiązanie

    return annotations_fields2;
  }

  /**
   * Metoda powinna wyszukać wszystkie zadeklarowane bezpośrednio w klasie metody oraz te
   * implementowane przez nią pochodzące z interfejsów, które implementuje. Wynik powinien zawierać
   * tylko unikalne nazwy metod (bez powtórzeń).
   *
   * @param type klasa (typ) poddawany analizie
   * @return lista zawierająca tylko unikalne nazwy metod zadeklarowanych przez klasę oraz te
   * implementowane
   */
  public static Collection<String> getAllDeclaredMethods(final Class<?> type) {
    List<String> annotations_methods =  new ArrayList<>();
    Method[] methods = type.getDeclaredMethods();

    for(Method method : methods)
    {
        annotations_methods.add(method.getName());
    }

    for (Class interfaces : type.getInterfaces())
    {
      for (Method method : interfaces.getDeclaredMethods())
      {
        annotations_methods.add(method.getName());
      }
    }

    HashSet<String> annotations_methods_set = new HashSet<>(annotations_methods);
    List<String> annotations_methods2 = new ArrayList<>(annotations_methods_set);

    //TODO usuń zawartość tej metody i umieść tutaj swoje rozwiązanie
    return annotations_methods2;
  }

  /**
   * Metoda powinna odszukać konstruktor zadeklarowany w podanej klasie który przyjmuje wszystkie
   * podane parametry wejściowe. Należy tak przygotować implementację aby nawet w przypadku gdy
   * pasujący konstruktor jest prywatny udało się poprawnie utworzyć nową instancję obiektu
   * <p>
   * Przykładowe użycia:
   * <code>ClassInspector.createInstance(Villager.class)</code>
   * <code>ClassInspector.createInstance(Villager.class, "Nazwa", "Opis")</code>
   *
   * @param type klasa (typ) którego instancje ma zostać utworzona
   * @param args parametry które mają zostać przekazane do konstruktora
   * @return nowa instancja klasy podanej jako parametr zainicjalizowana podanymi parametrami
   * @throws Exception wyjątek spowodowany nie znalezieniem odpowiedniego konstruktora
   */
  public static <T> T createInstance(final Class<T> type, final Object... args) throws Exception {

    Class[] params = new Class [args.length];
    for (int i = 0; i < params.length; i++)
    {
      params[i] = args[i].getClass();
    }

    Constructor<T> constructor;
    try {
      constructor = type.getDeclaredConstructor(params);
    } catch (NoSuchMethodException e) {
      throw new Exception("Nie znaleziono odpowiedniego konstruktora.", e);
    }
    constructor.setAccessible(true);

    //TODO usuń zawartość tej metody i umieść tutaj swoje rozwiązanie
    return constructor.newInstance(args);
  }
}
