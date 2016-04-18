package ghignatti.joao.plugin.verifiers;

import ghignatti.joao.plugin.array.ArrayTradeRequisition;

import org.bukkit.entity.Player;

public class RequisitionVerifier {
    private static RequisitionVerifier ourInstance = new RequisitionVerifier();

    public static RequisitionVerifier getInstance() {
        return ourInstance;
    }

    private RequisitionVerifier() {
    }

    public Player searchRequisition(Player t) {

        ArrayTradeRequisition arrayList = ArrayTradeRequisition.getInstance();

        for(int i = arrayList.requisitionArray.size()-1; i>=0; i--) {

            Player target = arrayList.requisitionArray.get(i).getTarget();

            if(t.equals(target)) {

                if(arrayList.requisitionArray.get(i).isStatus()) {

                    if (arrayList.requisitionArray.get(i).getTime() + 30000 > System.currentTimeMillis()) {

                        Player player = arrayList.requisitionArray.get(i).getPlayer();

                        if(player == null) {

                            target.sendMessage("O player que solicitou a troca não está mais online.");
                            return null;
                        } else {

                            return player;
                        }
                    } else {

                        arrayList.requisitionArray.get(i).setStatus(false);
                        target.sendMessage("Solicitação de troca expirou.");
                        return null;
                    }
                } else {

                    target.sendMessage("Solicitação de troca expirou.");
                    return null;
                }
            }
        }

        t.sendMessage("Você não foi solicitado a fazer troca.");
        return null;
    }
}
