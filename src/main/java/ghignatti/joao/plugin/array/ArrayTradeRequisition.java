package ghignatti.joao.plugin.array;

import ghignatti.joao.plugin.utilities.TradeRequisition;

import java.util.ArrayList;

public class ArrayTradeRequisition {
    private static ArrayTradeRequisition ourInstance = new ArrayTradeRequisition();

    public static ArrayTradeRequisition getInstance() {
        return ourInstance;
    }

    private ArrayTradeRequisition() {
    }

    public ArrayList<TradeRequisition> requisitionArray = new ArrayList<TradeRequisition>();
}
