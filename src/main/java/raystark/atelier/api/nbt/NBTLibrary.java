package raystark.atelier.api.nbt;

import net.minecraft.nbt.*;

import java.util.Set;
import java.util.function.IntToDoubleFunction;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static net.minecraftforge.common.util.Constants.NBT.*;

public final class NBTLibrary {
    private NBTLibrary() {throw new UnsupportedOperationException();}

    public static boolean isTagEnd(int id)          { return id == TAG_END;         } //0
    public static boolean isTagByte(int id)         { return id == TAG_BYTE;        } //1
    public static boolean isTagShort(int id)        { return id == TAG_SHORT;       } //2
    public static boolean isTagInt(int id)          { return id == TAG_INT;         } //3
    public static boolean isTagLong(int id)         { return id == TAG_LONG;        } //4
    public static boolean isTagFloat(int id)        { return id == TAG_FLOAT;       } //5
    public static boolean isTagDouble(int id)       { return id == TAG_DOUBLE;      } //6
    public static boolean isTagByteArray(int id)    { return id == TAG_BYTE_ARRAY;  } //7
    public static boolean isTagString(int id)       { return id == TAG_STRING;      } //8
    public static boolean isTagList(int id)         { return id == TAG_LIST;        } //9
    public static boolean isTagCompound(int id)     { return id == TAG_COMPOUND;    } //10
    public static boolean isTagIntArray(int id)     { return id == TAG_INT_ARRAY;   } //11
    public static boolean isTagAnyNumeric(int id)   { return id == TAG_ANY_NUMERIC; } //99

    public static boolean isTagEnd(NBTBase nbt)         { return nbt instanceof NBTTagEnd;       }
    public static boolean isTagByte(NBTBase nbt)        { return nbt instanceof NBTTagByte;      }
    public static boolean isTagShort(NBTBase nbt)       { return nbt instanceof NBTTagShort;     }
    public static boolean isTagInt(NBTBase nbt)         { return nbt instanceof NBTTagInt;       }
    public static boolean isTagLong(NBTBase nbt)        { return nbt instanceof NBTTagLong;      }
    public static boolean isTagFloat(NBTBase nbt)       { return nbt instanceof NBTTagFloat;     }
    public static boolean isTagDouble(NBTBase nbt)      { return nbt instanceof NBTTagDouble;    }
    public static boolean isTagByteArray(NBTBase nbt)   { return nbt instanceof NBTTagByteArray; }
    public static boolean isTagString(NBTBase nbt)      { return nbt instanceof NBTTagString;    }
    public static boolean isTagList(NBTBase nbt)        { return nbt instanceof NBTTagList;      }
    public static boolean isTagCompound(NBTBase nbt)    { return nbt instanceof NBTTagCompound;  }
    public static boolean isTagIntArray(NBTBase nbt)    { return nbt instanceof NBTTagIntArray;  }

    public static long asLong(NBTBase.NBTPrimitive nbtPrimitive) {
        requireNonNull(nbtPrimitive, "nbtPrimitive must not be null.");
        return nbtPrimitive.func_150291_c();
    }

    public static int asInt(NBTBase.NBTPrimitive nbtPrimitive) {
        requireNonNull(nbtPrimitive, "nbtPrimitive must not be null.");
        return nbtPrimitive.func_150287_d();
    }

    public static short asShort(NBTBase.NBTPrimitive nbtPrimitive) {
        requireNonNull(nbtPrimitive, "nbtPrimitive must not be null.");
        return nbtPrimitive.func_150289_e();
    }

    public static byte asByte(NBTBase.NBTPrimitive nbtPrimitive) {
        requireNonNull(nbtPrimitive, "nbtPrimitive must not be null.");
        return nbtPrimitive.func_150290_f();
    }

    public static double asDouble(NBTBase.NBTPrimitive nbtPrimitive) {
        requireNonNull(nbtPrimitive, "nbtPrimitive must not be null.");
        return nbtPrimitive.func_150286_g();
    }

    public static float asFloat(NBTBase.NBTPrimitive nbtPrimitive) {
        requireNonNull(nbtPrimitive, "nbtPrimitive must not be null.");
        return nbtPrimitive.func_150288_h();
    }

    public static int getTagType(NBTTagList tagList) {
        requireNonNull(tagList, "tagList must not be null.");
        return tagList.func_150303_d();
    }

    public static Stream<NBTTagCompound> asCompoundStream(NBTTagList tagList) {
        requireNonNull(tagList, "tagList must not be null.");
        if(!isTagCompound(getTagType(tagList)))
            throw new IllegalArgumentException("Incorrect tag type.");

        Stream.Builder<NBTTagCompound> builder = Stream.builder();
        for(int i=0; i<tagList.tagCount(); i++)
            builder.add(tagList.getCompoundTagAt(i));

        return builder.build();
    }

    public static Stream<int[]> asIntArrayStream(NBTTagList tagList) {
        requireNonNull(tagList, "tagList must not be null.");
        if(!isTagIntArray(getTagType(tagList)))
            throw new IllegalArgumentException("Incorrect tag type.");

        Stream.Builder<int[]> builder = Stream.builder();
        for(int i=0; i<tagList.tagCount(); i++)
            builder.add(tagList.func_150306_c(i));

        return builder.build();
    }

    public static DoubleStream asDoubleStream(NBTTagList tagList) {
        requireNonNull(tagList, "tagList must not be null.");

        IntToDoubleFunction getTagAsDoubleAt = getTagAsDoubleAt(tagList);
        DoubleStream.Builder builder = DoubleStream.builder();
        for(int i=0; i<tagList.tagCount(); i++)
            builder.add(getTagAsDoubleAt.applyAsDouble(i));
        return builder.build();
    }

    private static IntToDoubleFunction getTagAsDoubleAt(NBTTagList tagList) {
        int tagType = getTagType(tagList);

        if(isTagDouble(tagType))
            return tagList::func_150309_d;
        if(isTagFloat(tagType))
            return tagList::func_150308_e;
        else throw new IllegalArgumentException("Incorrect tag type.");
    }

    public static Stream<String> asStringStream(NBTTagList tagList) {
        requireNonNull(tagList, "tagList must not be null.");
        if(!isTagString(getTagType(tagList)))
            throw new IllegalArgumentException("Incorrect tag type.");

        Stream.Builder<String> builder = Stream.builder();
        for(int i=0; i<tagList.tagCount(); i++)
            builder.add(tagList.getStringTagAt(i));

        return builder.build();
    }

    public static IntStream asIntStream(NBTTagIntArray tagIntArray) {
        requireNonNull(tagIntArray, "tagIntArray must not be null.");
        return IntStream.of(tagIntArray.func_150302_c());
    }

    public static IntStream asIntStream(NBTTagByteArray tagByteArray) {
        requireNonNull(tagByteArray, "tagByteArray must not be null.");

        IntStream.Builder builder = IntStream.builder();
        for(byte i : tagByteArray.func_150292_c())
            builder.add(i);

        return builder.build();
    }

    @SuppressWarnings("unchecked") //NBTのキーはString型
    public static Set<String> keySet(NBTTagCompound tagCompound) {
        return requireNonNull(tagCompound, "tagCompound must not be null.")
                .func_150296_c();
    }

    public static Stream<String> keySetStream(NBTTagCompound tagCompound) {
        return keySet(tagCompound).stream();
    }

    public static Stream<NBTBase> asNbtBaseStream(NBTTagCompound tagCompound) {
        return keySetStream(tagCompound).map(tagCompound::getTag);
    }
}
