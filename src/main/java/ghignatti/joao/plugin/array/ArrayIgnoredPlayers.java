package ghignatti.joao.plugin.array;

import ghignatti.joao.plugin.utilities.IgnoredPlayers;

import java.util.ArrayList;

public class ArrayIgnoredPlayers {
    private static ArrayIgnoredPlayers ourInstance = new ArrayIgnoredPlayers();

    public static ArrayIgnoredPlayers getInstance() {
        return ourInstance;
    }

    private ArrayIgnoredPlayers() {
    }

    public ArrayList<IgnoredPlayers> ignoredPlayers = new ArrayList<IgnoredPlayers>();
}
