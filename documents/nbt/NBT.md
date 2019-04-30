# 本Modで扱うNBTについて

## 構造
* NBTTagCompound : getTagCompoundで取得できるタグ
    * NBTTagCompound("ModAtelier") : 本Modのタグ
        * NBTTagInteger("Quality") : 品質値
        * NBTTagList("Effect") : 効果のリスト
            * Effect1
            * Effect2
            * ...
        * NBTTagList("Ability") : 潜在能力のリスト
            * Ability1
            * Ability2
            * ...

## 実装

###タグを付与するタイミング
