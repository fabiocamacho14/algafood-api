import com.algaworks.algafood.domain.model.Estado;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class FastTest {

    @Test
    public void fastTest() {
        Estado estado = new Estado(56, "Tocantins");

        Set<Estado> estados = new HashSet<>();
        System.out.println(estados.add(estado));
        System.out.println(estados.remove(estado));
        System.out.println(estados.remove(estado));

        for (Estado estadoList : estados) {
            System.out.println("ID: " + estadoList.getId() + " Nome: " + estado.getNome());
        }
    }
}
