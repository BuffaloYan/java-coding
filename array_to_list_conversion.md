# Converting int[] Array to List<Integer> in Java

There are several ways to convert an int array to a List in Java:

1. Using Arrays.asList() with manual boxing:
```java
int[] arr = {1, 2, 3, 4, 5};
List<Integer> list = Arrays.stream(arr)
                        .boxed()
                        .collect(Collectors.toList());
```

2. Using Arrays.stream():
```java
int[] arr = {1, 2, 3, 4, 5};
List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
```

3. Using manual conversion with a loop:
```java
int[] arr = {1, 2, 3, 4, 5};
List<Integer> list = new ArrayList<>();
for (int num : arr) {
    list.add(num);
}
```

Note: You cannot use `Arrays.asList(arr)` directly with a primitive int array because it will create a List<int[]> instead of List<Integer>. This is why we need to use streaming or manual conversion to properly box the primitive ints into Integer objects.

Important: Don't forget to include these imports:
```java
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
```