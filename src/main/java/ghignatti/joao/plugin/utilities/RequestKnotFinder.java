package ghignatti.joao.plugin.utilities;

import ghignatti.joao.plugin.array.ArrayTradeRequisition;
import org.bukkit.entity.Player;

public class RequestKnotFinder {
    private static RequestKnotFinder ourInstance = new RequestKnotFinder();

    public static RequestKnotFinder getInstance() {
        return ourInstance;
    }

    private RequestKnotFinder() {
    }

    public int findKnot(Player t) {

        ArrayTradeRequisition arrayList = ArrayTradeRequisition.getInstance();

        for(int i = arrayList.requisitionArray.size()-1; i>=0; i--) {

            Player target = arrayList.requisitionArray.get(i).getTarget();

            if(t.equals(target)) {

                if(arrayList.requisitionArray.get(i).isStatus()) {

                    if (arrayList.requisitionArray.get(i).getTime() + 30000 > System.currentTimeMillis()) {

                        return i;
                    } else {

                        arrayList.requisitionArray.get(i).setStatus(false);
                        target.sendMessage("Solicitação de troca já expirou.");
                        return i;
                    }
                } else {

                    target.sendMessage("Solicitação de troca já expirou.");
                    return i;
                }
            }
        }

        return -1;
    }
}
