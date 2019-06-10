package raystark.atelier.api.alchemy.status;


/**
 * 錬金素材の性能を表すインターフェース。
 *
 * <p>錬金術クラフトにおいて材料の属性値は各アイテム、ブロックに付き固定値となるが、
 * 品質値と潜在能力は材料に用いたアイテムに直接付与された値が利用される。材料として
 * 用いたアイテムに能力が何も付与されていなかった場合、デフォルトの能力値が適応され
 * る。
 */
public interface IMaterialStatus extends Quality, ElementOwner, PotentialAbilityOwner {}
