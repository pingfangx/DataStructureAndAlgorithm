/*
 * Copyright (c) 1997, 2017, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package java2.util;

import sun.misc.SharedSecrets;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 介绍和公共方法是从 1.6 的 中文 api 来的，官方的翻译可能会好一些。
 * 实现说明和非公共方法就没有官方的翻译了，是自己翻译的可能不准确。
 * <p>
 * Hash table based implementation of the <tt>Map</tt> interface.  This
 * implementation provides all of the optional map operations, and permits
 * <tt>null</tt> values and the <tt>null</tt> key.  (The <tt>HashMap</tt>
 * class is roughly equivalent to <tt>Hashtable</tt>, except that it is
 * unsynchronized and permits nulls.)  This class makes no guarantees as to
 * the order of the map; in particular, it does not guarantee that the order
 * will remain constant over time.
 * <p>
 * 基于哈希表的 Map 接口的实现。此实现提供所有可选的映射操作，并允许使用 null 值和 null 键。
 * （除了非同步和允许使用 null 之外，HashMap 类与 Hashtable 大致相同。）
 * 此类不保证映射的顺序，特别是它不保证该顺序恒久不变。
 * <p>
 * Q:为什么不能保证顺序
 * 因为迭代时使用的 HashIterator ，它内部根据 index 迭代，index 是由 hash(key) 算出来的，所以与 key 没有顺序关系。
 * <p>
 * <p>This implementation provides constant-time performance for the basic
 * operations (<tt>get</tt> and <tt>put</tt>), assuming the hash function
 * disperses the elements properly among the buckets.  Iteration over
 * collection views requires time proportional to the "capacity" of the
 * <tt>HashMap</tt> instance (the number of buckets) plus its size (the number
 * of key-value mappings).  Thus, it's very important not to set the initial
 * capacity too high (or the load factor too low) if iteration performance is
 * important.
 * <p>
 * 此实现假定哈希函数将元素适当地分布在各桶之间，可为基本操作（get 和 put）提供稳定的性能。
 * 迭代 collection 视图所需的时间与 HashMap 实例的“容量”（桶的数量）及其大小（键-值映射关系数）成比例。
 * 所以，如果迭代性能很重要，则不要将初始容量设置得太高（或将加载因子设置得太低）。
 * <p>
 * Q:TODO 为什么稳定？性能是多少？
 * <p>
 * <p>An instance of <tt>HashMap</tt> has two parameters that affect its
 * performance: <i>initial capacity</i> and <i>load factor</i>.  The
 * <i>capacity</i> is the number of buckets in the hash table, and the initial
 * capacity is simply the capacity at the time the hash table is created.  The
 * <i>load factor</i> is a measure of how full the hash table is allowed to
 * get before its capacity is automatically increased.  When the number of
 * entries in the hash table exceeds the product of the load factor and the
 * current capacity, the hash table is <i>rehashed</i> (that is, internal data
 * structures are rebuilt) so that the hash table has approximately twice the
 * number of buckets.
 * <p>
 * HashMap 的实例有两个参数影响其性能：初始容量 和加载因子。
 * 容量 是哈希表中桶的数量，初始容量只是哈希表在创建时的容量。
 * 加载因子 是哈希表在其容量自动增加之前可以达到多满的一种尺度。
 * 当哈希表中的条目数超出了加载因子与当前容量的乘积时，则要对该哈希表进行 rehash 操作（即重建内部数据结构），
 * 从而哈希表将具有大约两倍的桶数。
 * <p>
 * 这里应该是文档没有更新，在 1.8 中是没有 rehash 的操作的
 * 不过翻译一下， 1.7 中的方法也是叫 resize ，然后在 transfer 中根据参数判断重新计算 hash
 * 在 1.6 是不判断的，直接 rehash
 * <p>
 * <p>As a general rule, the default load factor (.75) offers a good
 * tradeoff between time and space costs.  Higher values decrease the
 * space overhead but increase the lookup cost (reflected in most of
 * the operations of the <tt>HashMap</tt> class, including
 * <tt>get</tt> and <tt>put</tt>).  The expected number of entries in
 * the map and its load factor should be taken into account when
 * setting its initial capacity, so as to minimize the number of
 * rehash operations.  If the initial capacity is greater than the
 * maximum number of entries divided by the load factor, no rehash
 * operations will ever occur.
 * <p>
 * 通常，默认加载因子 (.75) 在时间和空间成本上寻求一种折衷。
 * 加载因子过高虽然减少了空间开销，但同时也增加了查询成本
 * （在大多数 HashMap 类的操作中，包括 get 和 put 操作，都反映了这一点）。
 * 在设置初始容量时应该考虑到映射中所需的条目数及其加载因子，以便最大限度地减少 rehash 操作次数。
 * 如果初始容量大于最大条目数除以加载因子，则不会发生 rehash 操作。
 * <p>
 * <p>If many mappings are to be stored in a <tt>HashMap</tt>
 * instance, creating it with a sufficiently large capacity will allow
 * the mappings to be stored more efficiently than letting it perform
 * automatic rehashing as needed to grow the table.  Note that using
 * many keys with the same {@code hashCode()} is a sure way to slow
 * down performance of any hash table. To ameliorate impact, when keys
 * are {@link Comparable}, this class may use comparison order among
 * keys to help break ties.
 * <p>
 * 如果很多映射关系要存储在 HashMap 实例中，则相对于按需执行自动的 rehash 操作以增大表的容量来说，
 * 使用足够大的初始容量创建它将使得映射关系能更有效地存储。
 * 请注意，使用具有相同 {@code hashCode()} 的多个 key 可以减慢任何哈希表的性能。
 * 为了改善影响，当键是 {@link Comparable} 时，这个类可以使用键之间的比较顺序来帮助打破关系。
 * <p>
 * <p><strong>Note that this implementation is not synchronized.</strong>
 * If multiple threads access a hash map concurrently, and at least one of
 * the threads modifies the map structurally, it <i>must</i> be
 * synchronized externally.  (A structural modification is any operation
 * that adds or deletes one or more mappings; merely changing the value
 * associated with a key that an instance already contains is not a
 * structural modification.)  This is typically accomplished by
 * synchronizing on some object that naturally encapsulates the map.
 * <p>
 * 注意，此实现不是同步的。
 * 如果多个线程同时访问一个哈希映射，而其中至少一个线程从结构上修改了该映射，则它 必须 保持外部同步。
 * （结构上的修改是指添加或删除一个或多个映射关系的任何操作；仅改变与实例已经包含的键关联的值不是结构上的修改。）
 * 这一般通过对自然封装该映射的对象进行同步操作来完成。
 * <p>
 * If no such object exists, the map should be "wrapped" using the
 * {@link Collections#synchronizedMap Collections.synchronizedMap}
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the map:<pre>
 *   Map m = Collections.synchronizedMap(new HashMap(...));</pre>
 * <p>
 * 如果不存在这样的对象，则应该使用 Collections.synchronizedMap 方法来“包装”该映射。
 * 最好在创建时完成这一操作，以防止对映射进行意外的非同步访问，如下所示：
 * <p>
 * <p>The iterators returned by all of this class's "collection view methods"
 * are <i>fail-fast</i>: if the map is structurally modified at any time after
 * the iterator is created, in any way except through the iterator's own
 * <tt>remove</tt> method, the iterator will throw a
 * {@link ConcurrentModificationException}.  Thus, in the face of concurrent
 * modification, the iterator fails quickly and cleanly, rather than risking
 * arbitrary, non-deterministic behavior at an undetermined time in the
 * future.
 * <p>
 * 由所有此类的“collection 视图方法”所返回的迭代器都是快速失败 的：
 * 在迭代器创建之后，如果从结构上对映射进行修改，除非通过迭代器本身的 remove 方法，其他任何时间任何方式的修改，
 * 迭代器都将抛出 ConcurrentModificationException。
 * 因此，面对并发的修改，迭代器很快就会完全失败，而不冒在将来不确定的时间发生任意不确定行为的风险。
 * <p>
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw <tt>ConcurrentModificationException</tt> on a best-effort basis.
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness: <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i>
 * <p>
 * 注意，迭代器的快速失败行为不能得到保证，一般来说，存在非同步的并发修改时，不可能作出任何坚决的保证。
 * 快速失败迭代器尽最大努力抛出 ConcurrentModificationException。
 * 因此，编写依赖于此异常的程序的做法是错误的，正确做法是：迭代器的快速失败行为应该仅用于检测程序错误。
 * <p>
 * <p>This class is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 * 此类是 Java Collections Framework 的成员。
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 * @author Doug Lea
 * @author Josh Bloch
 * @author Arthur van Hoff
 * @author Neal Gafter
 * @see Object#hashCode()
 * @see Collection
 * @see Map
 * @see TreeMap
 * @see Hashtable
 * @since 1.2
 */
public class HashMap<K, V> extends AbstractMap<K, V>
        implements Cloneable, Serializable {

    private static final long serialVersionUID = 362498820763181265L;

    /*
     * Implementation notes.
     * 实现说明
     *
     * This map usually acts as a binned (bucketed) hash table, but
     * when bins get too large, they are transformed into bins of
     * TreeNodes, each structured similarly to those in
     * TreeMap. Most methods try to use normal bins, but
     * relay to TreeNode methods when applicable (simply by checking
     * instanceof a node).  Bins of TreeNodes may be traversed and
     * used like any others, but additionally support faster lookup
     * when overpopulated. However, since the vast majority of bins in
     * normal use are not overpopulated, checking for existence of
     * tree bins may be delayed in the course of table methods.
     *
     * bin 箱，bucket 桶，因为之前很多都是桶，以下 bin 可能翻译为桶
     *
     * 该 map 通常用作分桶的哈希表，但当 bin 过大时，它们会转换为 TreeNodes （树节点）的 bin，
     * 其结构与 TreeMap 中的结构类似。 大多数方法都尝试使用正常的 bin，但在适用时转发给 TreeNode 方法
     * （仅使用 instanceof 检查节点）。TreeNodes 的 bin 可以像任何其他的一样被遍历和使用，
     * 但是数量过多时还支持更快的查找。然而，大多数情况下 bin 容量不会过大，
     * 检查树 bin 的存在可能会延时到 table 方法中。
     *
     * Tree bins (i.e., bins whose elements are all TreeNodes) are
     * ordered primarily by hashCode, but in the case of ties, if two
     * elements are of the same "class C implements Comparable<C>",
     * type then their compareTo method is used for ordering. (We
     * conservatively check generic types via reflection to validate
     * this -- see method comparableClassFor).  The added complexity
     * of tree bins is worthwhile in providing worst-case O(log n)
     * operations when keys either have distinct hashes or are
     * orderable, Thus, performance degrades gracefully under
     * accidental or malicious usages in which hashCode() methods
     * return values that are poorly distributed, as well as those in
     * which many keys share a hashCode, so long as they are also
     * Comparable. (If neither of these apply, we may waste about a
     * factor of two in time and space compared to taking no
     * precautions. But the only known cases stem from poor user
     * programming practices that are already so slow that this makes
     * little difference.)
     * Tree bins （即 TreeNode 的 bin）主要通过 hashCode 排序，
     * 但如果两个元素都是 class C implements Comparable<C> 的类型，将会使用它们的 compareTo 方法来排序。
     * 我们谨慎地通过反射检查泛型类型来验证是否是 Comparable，见 comparableClassFor 方法。
     * tree bins 所增加的复杂度是值得的，当 keys 具有不同的哈希或者可排序时，都提供最坏 O(log n) 的操作。
     * 因此，在无意或有意的使用中，如果 hashCode() 返回的值分布不均，或者多个 key 共享一个 hashCode，
     * 只要它们是 Comparable，性能地下降也会平滑。
     * （如果两者都不适用，那么与未采取预防措施相比，我们可能浪费大约两倍的时间和空间。
     * 但是，唯一已知的情况源于糟糕的用户编程实践，这些实践已经非常缓慢，使得几乎没有区别）
     *
     * Because TreeNodes are about twice the size of regular nodes, we
     * use them only when bins contain enough nodes to warrant use
     * (see TREEIFY_THRESHOLD). And when they become too small (due to
     * removal or resizing) they are converted back to plain bins.  In
     * usages with well-distributed user hashCodes, tree bins are
     * rarely used.  Ideally, under random hashCodes, the frequency of
     * nodes in bins follows a Poisson distribution
     * (http://en.wikipedia.org/wiki/Poisson_distribution) with a
     * parameter of about 0.5 on average for the default resizing
     * threshold of 0.75, although with a large variance because of
     * resizing granularity. Ignoring variance, the expected
     * occurrences of list size k are (exp(-0.5) * pow(0.5, k) /
     * factorial(k)). The first values are:
     * 由于 TreeNodes 大约是常规节点大小的两倍，因此我们仅在容器包含足够的节点以保证使用时才使用它们
     * （请参阅 TREEIFY_THRESHOLD ）。当它们变得太小时（由于移除或调整大小），它们被转换回普通 bins。
     * 在具有分布良好的用户散列码的使用中，很少使用 tree bins。理想情况下，在随机 hashCodes 下，
     * 对于默认的 resizing 阈值 0.75 ，
     * bin 中节点的频率遵循泊松分布（http://en.wikipedia.org/wiki/Poisson_distribution），参数约为 0.5，
     * ，尽管由于 resizing 粒度会具有很大的变动。
     * 忽略变动，大小为 k 的列表的预期出现为 (exp(-0.5) * pow(0.5, k) / factorial(k))，前几个值为：
     *
     * 这里实在不太理解，见 https://stackoverflow.com/questions/20448477
     * 不过好像是指桶中数量大于 8 的概率很小很小
     *
     *
     *
     * 0:    0.60653066
     * 1:    0.30326533
     * 2:    0.07581633
     * 3:    0.01263606
     * 4:    0.00157952
     * 5:    0.00015795
     * 6:    0.00001316
     * 7:    0.00000094
     * 8:    0.00000006
     * more: less than 1 in ten million
     * 更多的，小于千万分之一。
     *
     * The root of a tree bin is normally its first node.  However,
     * sometimes (currently only upon Iterator.remove), the root might
     * be elsewhere, but can be recovered following parent links
     * (method TreeNode.root()).
     * 树 bin 的根节点通常是它的第一个节点。但是，有时（当前仅在 Iterator.remove 时）根可能在其他地方，
     * 但可以用父链接取得（方法 TreeNode.root()）
     *
     * All applicable internal methods accept a hash code as an
     * argument (as normally supplied from a public method), allowing
     * them to call each other without recomputing user hashCodes.
     * Most internal methods also accept a "tab" argument, that is
     * normally the current table, but may be a new or old one when
     * resizing or converting.
     * 所有适用的内部方法都接受散列码作为参数（通常由公共方法提供），允许它们互相调用而不用重新计算 hash。
     * 大多数内部方法也接受一个 “tab” 参数，通常是当前表，但在调整大小或转换时可能是新的或旧的。
     *
     * When bin lists are treeified, split, or untreeified, we keep
     * them in the same relative access/traversal order (i.e., field
     * Node.next) to better preserve locality, and to slightly
     * simplify handling of splits and traversals that invoke
     * iterator.remove. When using comparators on insertion, to keep a
     * total ordering (or as close as is required here) across
     * rebalancings, we compare classes and identityHashCodes as
     * tie-breakers.
     * 当 bin 列表被树化，分裂或者取消树化时，
     * 我们使它们保持相同的相对访问/遍历顺序（即，字段 Node.next）以更好地保持局部性，
     * 并略微简化调用 iterator.remove 的分割和遍历的处理。在插入时使用 comparator 时，
     * 为了在整个重定位过程中保持总体排序（或尽可能接近此处所需），
     * 我们比较类和 identityHashCodes 用作联合破坏。
     *
     * The use and transitions among plain vs tree modes is
     * complicated by the existence of subclass LinkedHashMap. See
     * below for hook methods defined to be invoked upon insertion,
     * removal and access that allow LinkedHashMap internals to
     * otherwise remain independent of these mechanics. (This also
     * requires that a map instance be passed to some utility methods
     * that may create new nodes.)
     * 由于存在子类 LinkedHashMap，在普通模式和树模式之间的使用和转换变得复杂了。
     * 请参阅下面的钩子方法，这些钩子方法被定义为在插入，移除和访问时被调用，
     * 以允许 LinkedHashMap 内部部件保持独立于这些机制。 （这也要求映射实例被传递给可能创建新节点的一些实用方法。）
     *
     * The concurrent-programming-like SSA-based coding style helps
     * avoid aliasing errors amid all of the twisty pointer operations.
     * 类似于并发编程的基于 SSA 的编码风格有助于避免所有扭曲的指针操作中的别名错误。
     */

    /**
     * The default initial capacity - MUST be a power of two.
     * <p>
     * 默认初始容量 - 必须是 2 的幂。
     */
    // Q: 为什么要是 2 的幂？
    // 为了取 index 时能够均匀分布
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     * <p>
     * 最大容量，如果任何一个带参数的构造函数隐式指定了较高的值，则置为该最值。
     * 必须是 2 的幂，<= 1<<30
     * <p>
     * Q:如何生效的
     * 就是在构造函数中判断，如果大于此值，则置为此值
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The load factor used when none specified in constructor.
     * <p>
     * 当没有在构造函数中指定时，所使用的加载因子。
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * The bin count threshold for using a tree rather than list for a
     * bin.  Bins are converted to trees when adding an element to a
     * bin with at least this many nodes. The value must be greater
     * than 2 and should be at least 8 to mesh with assumptions in
     * tree removal about conversion back to plain bins upon
     * shrinkage.
     * <p>
     * 使用树而不是列表的 bin 数阈值。
     * 将元素添加到 bin 中时，如果 bin 至少包含该数量的结点时，该 bin 将转换为树。
     * 该值必须大于2，并且应该至少为8，以便满足在收缩时移除树转换回普通 bin 的假设。
     * <p>
     * Q:为什么至少为8，怎么翻译
     * 可能是因为上面实现说明中解释的 8
     */
    static final int TREEIFY_THRESHOLD = 8;

    /**
     * The bin count threshold for untreeifying a (split) bin during a
     * resize operation. Should be less than TREEIFY_THRESHOLD, and at
     * most 6 to mesh with shrinkage detection under removal.
     * <p>
     * 用于 resize 期间对 bin 取消树化（分割）的 bin 数阈值。
     * 应该小于 TREEIFY_THRESHOLD ，并且最大为6，以吻合移除期间的收缩检测。
     */
    static final int UNTREEIFY_THRESHOLD = 6;

    /**
     * The smallest table capacity for which bins may be treeified.
     * (Otherwise the table is resized if too many nodes in a bin.)
     * Should be at least 4 * TREEIFY_THRESHOLD to avoid conflicts
     * between resizing and treeification thresholds.
     * <p>
     * bins 可能被树化的最小表容量。（否则，如果bin中的节点太多，则调整表的大小。）
     * 应该至少为 4 * TREEIFY_THRESHOLD 以避免调整大小和树化阈值之间的冲突。
     */
    static final int MIN_TREEIFY_CAPACITY = 64;

    /**
     * Basic hash bin node, used for most entries.  (See below for
     * TreeNode subclass, and in LinkedHashMap for its Entry subclass.)
     * <p>
     * 基本的哈希 bin 节点，用于大多数 entry。 （请参阅下面的 TreeNode 子类，以及 LinkedHashMap 中的 Entry 子类。）
     */
    static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final String toString() {
            return key + "=" + value;
        }

        public final int hashCode() {
            /*
             * Q: 为什么用异或 ^
             * 可能是因为，假设 2 个 hash ，某一位分别可能为 1 或 0，则有 4 种组合，
             * 将它们合并，可能产生 1 、 0 两种结果
             * 如是用 & ，概率为 1:3
             * 如果用 | ，概率为 3:1
             * 只有用 ^ ，才能是 2:2
             * 不知道是不是这个原因
             */
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

    /* ---------------- Static utilities -------------- */
    /* ---------------- 静态工具 -------------- */

    /**
     * Computes key.hashCode() and spreads (XORs) higher bits of hash
     * to lower.  Because the table uses power-of-two masking, sets of
     * hashes that vary only in bits above the current mask will
     * always collide. (Among known examples are sets of Float keys
     * holding consecutive whole numbers in small tables.)  So we
     * apply a transform that spreads the impact of higher bits
     * downward. There is a tradeoff between speed, utility, and
     * quality of bit-spreading. Because many common sets of hashes
     * are already reasonably distributed (so don't benefit from
     * spreading), and because we use trees to handle large sets of
     * collisions in bins, we just XOR some shifted bits in the
     * cheapest possible way to reduce systematic lossage, as well as
     * to incorporate impact of the highest bits that would otherwise
     * never be used in index calculations because of table bounds.
     * <p>
     * 计算 key.hashCode() 并散布（XORs）散列的较高位到低位。
     * 由于表使用 2 的幂级别的掩码，所以只在当前掩码之上的位上发生变化的散列总是会发生冲突。
     * （在已知的例子中有：在小表中，以 Float 为键的集合持有连续的整数）。
     * 所以我们应用一个转换，将高位的影响向下扩展。
     * 这是在速度，效用和比特传播质量之间进行的权衡。
     * 因为许多常见的散列集合已经合理分布（所以不能从扩散中受益），
     * 并且因为我们使用树来处理 bins 中的大量碰撞，我们只以最便宜的方式对一些位移进行异或运算，
     * 以减少系统损失， 以及合并最高位的影响，这些最高位由于表边界而不会用于索引计算。
     * <p>
     * Q: 什么叫掩码
     * https://en.wikipedia.org/wiki/Mask_(computing)#Hash_tables 中介绍，其中就有 Hash tables 的部分
     * To create a hashing function for a hash table, often a function is used that has a large domain.
     * To create an index from the output of the function, a modulo can be taken to reduce the size of the
     * domain to match the size of the array; however, it is often faster on many processors to restrict the size of
     * the hash table to powers of two sizes and use a bitmask instead.
     * 同时还应该知道有意思的，AND 0 可以将某几位转为 0，OR 1 可以转为 1，XOR 1 可以反转
     * <p>
     * Q:Because the table uses power-of-two masking, sets of
     * hashes that vary only in bits above the current mask will
     * always collide.
     * 这一句可能是说，掩码之上（即之前）的位上的变化，掩码处理后总是相同导致冲突
     * <p>
     * Q:Among known examples are sets of Float keys
     * holding consecutive whole numbers in small tables
     * 该例子是 Float 的 hashCode 是 java.lang.Float#floatToIntBits(float)
     * 如果正负相同，指数相同，尾数变化不大的话，由于 22-0 位表示尾数，尾数只有前面部分（正好对应高位）不同，
     * 低位一整串都是 0
     */
    static final int hash(Object key) {
        int h;
        /*
         * Q: 为什么用异或 ^
         * 在前面 Node.hashCode 的有讨论
         */
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * Returns x's Class if it is of the form "class C implements
     * Comparable<C>", else null.
     * <p>
     * 如果 x 的形式为 "class C implements Comparable<C>"，则返回 x 的 Class，否则返回 null。
     * 注意这里要实现的必须为 Comparable<C> 泛型只能为 C 不能是其他，如父类等。
     * 见 ComparableClassForTest
     * <p>
     * Q:这里的判断为什么要这么严格
     * 不知道，可能是要排除子类的影响吧，但为什么要呢……
     */
    static Class<?> comparableClassFor(Object x) {
        if (x instanceof Comparable) {
            Class<?> c;
            Type[] ts, as;
            Type t;
            ParameterizedType p;
            if ((c = x.getClass()) == String.class) // bypass checks
                //String 特殊处理，因为它是 final 没有子类，不会有下述问题
                return c;
            if ((ts = c.getGenericInterfaces()) != null) {
                for (int i = 0; i < ts.length; ++i) {
                    if (((t = ts[i]) instanceof ParameterizedType) &&
                            ((p = (ParameterizedType) t).getRawType() ==
                                    Comparable.class) &&
                            (as = p.getActualTypeArguments()) != null &&
                            as.length == 1 && as[0] == c) // type arg is c
                        //必须 == c,子类在此处不等于父类
                        return c;
                }
            }
        }
        return null;
    }

    /**
     * Returns k.compareTo(x) if x matches kc (k's screened comparable
     * class), else 0.
     * <p>
     * 如果 x 匹配 kc(k 的筛选 comparable 类)，则返回 k.compareTo(x)，否则返回 0。
     */
    @SuppressWarnings({"rawtypes", "unchecked"}) // for cast to Comparable
    static int compareComparables(Class<?> kc, Object k, Object x) {
        return (x == null || x.getClass() != kc ? 0 :
                ((Comparable) k).compareTo(x));
    }

    /**
     * Returns a power of two size for the given target capacity.
     * <p>
     * 为给定的目标容量返回 2 的幂的尺寸。
     * 经过前面的几次无符号右移，最后全是1，要再加上 1 才是 2 的幂
     * 因为最后要加上 1 所以前面提前 - 1
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /* ---------------- Fields -------------- */
    /* ---------------- 字段 -------------- */

    /**
     * The table, initialized on first use, and resized as
     * necessary. When allocated, length is always a power of two.
     * (We also tolerate length zero in some operations to allow
     * bootstrapping mechanics that are currently not needed.)
     * <p>
     * 表，在初次使用时初始化，并根据需要调整大小。
     * 在分配时，长度总是 2 的幂。（我们也允许在某些操作中长度为零，以允许当前不需要的引导机制。）
     */
    transient Node<K, V>[] table;

    /**
     * Holds cached entrySet(). Note that AbstractMap fields are used
     * for keySet() and values().
     * <p>
     * 持有的缓存的 entrySet()。请注意，AbstractMap 字段用于 keySet() 和 values()。
     */
    transient Set<Map.Entry<K, V>> entrySet;

    /**
     * The number of key-value mappings contained in this map.
     * <p>
     * 此映射中包含的键值映射的数量。
     */
    transient int size;

    /**
     * The number of times this HashMap has been structurally modified
     * Structural modifications are those that change the number of mappings in
     * the HashMap or otherwise modify its internal structure (e.g.,
     * rehash).  This field is used to make iterators on Collection-views of
     * the HashMap fail-fast.  (See ConcurrentModificationException).
     * <p>
     * 这个 HashMap 被结构修改的次数
     * 结构修改是那些改变 HashMap 中的映射数量或修改其内部结构（例如，rehash）的修改。
     * 该字段用于在 HashMap 的 Collection 视图的迭代上产生快速失败。（请参阅 ConcurrentModificationException）。
     * <p>
     * Q:为什么这里要提 rehash
     * rehash 或 resize 相关方法中，并没有更改 modCount 呀，看了 1.7 也没有
     */
    transient int modCount;

    /**
     * The next size value at which to resize (capacity * load factor).
     * <p>
     * 下一次调整大小的值（容量*加载因子）
     *
     * @serial
     */
    // (The javadoc description is true upon serialization.
    // Additionally, if the table array has not been allocated, this
    // field holds the initial array capacity, or zero signifying
    // DEFAULT_INITIAL_CAPACITY.)
    // （在序列化时，javadoc 描述是真实的。此外，如果表格数组尚未分配，则此字段保存初始数组容量，或者零表示 DEFAULT_INITIAL_CAPACITY。）
    int threshold;

    /**
     * The load factor for the hash table.
     * <p>
     * 哈希表的加载因子。
     *
     * @serial
     */
    final float loadFactor;

    /* ---------------- Public operations -------------- */
    /* ---------------- 公共操作 -------------- */

    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and load factor.
     * <p>
     * 用指定的初始容量和加载因子构造一个空的 <tt>HashMap</tt> 。
     *
     * @param initialCapacity the initial capacity
     * @param loadFactor      the load factor
     * @throws IllegalArgumentException if the initial capacity is negative
     *                                  or the load factor is nonpositive
     *                                  <p>
     *                                  如果初始容量是负值
     *                                  或者加载因子是非正的
     */
    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and the default load factor (0.75).
     * <p>
     * 使用指定的初始容量和默认加载因子(0.75)构造一个空的 <tt>HashMap</tt> 。
     *
     * @param initialCapacity the initial capacity.
     * @throws IllegalArgumentException if the initial capacity is negative.
     */
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the default initial capacity
     * (16) and the default load factor (0.75).
     * <p>
     * 使用默认初始容量(16)和默认加载因子(0.75)构造一个空的 <tt>HashMap</tt> 。
     */
    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
        /*
         * Q:这里为什么不调用 this ，不对 threshold 初始化吗
         * 不需要,在 resize() 方法中会处理
         */

    }

    /**
     * Constructs a new <tt>HashMap</tt> with the same mappings as the
     * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
     * default load factor (0.75) and an initial capacity sufficient to
     * hold the mappings in the specified <tt>Map</tt>.
     * <p>
     * 使用与指定的 <tt>HashMap</tt> 相同的映射构造一个新的 <tt>HashMap</tt> 。
     * 该 <tt>HashMap</tt> 使用默认加载因子(0.75) 和足以容纳指定 <tt>HashMap</tt> 中的映射的初始容量。
     *
     * @param m the map whose mappings are to be placed in this map
     * @throws NullPointerException if the specified map is null
     */
    public HashMap(Map<? extends K, ? extends V> m) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntries(m, false);
    }

    /**
     * Implements Map.putAll and Map constructor
     * <p>
     * 实现 Map.putAll 和 Map 构造函数
     *
     * @param m     the map
     * @param evict false when initially constructing this map, else
     *              true (relayed to method afterNodeInsertion).
     *              <p>
     *              在初始化构造 map 时为 false,否则为 true
     *              传到方法 afterNodeInsertion
     */
    final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {
        int s = m.size();
        if (s > 0) {
            if (table == null) { // pre-size
                //得到容量
                /*
                 * Q:为什么要 +1
                 *可能是为了将 float 转为 int 时的丢失
                 */
                float ft = ((float) s / loadFactor) + 1.0F;
                int t = ((ft < (float) MAXIMUM_CAPACITY) ?
                        (int) ft : MAXIMUM_CAPACITY);
                if (t > threshold)
                    threshold = tableSizeFor(t);
            } else if (s > threshold)
                //可以看到，为空调的 tableSizeFor ，不为空判断后调 resize
                resize();
            for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
                K key = e.getKey();
                V value = e.getValue();
                putVal(hash(key), key, value, false, evict);
            }
        }
    }

    /**
     * Returns the number of key-value mappings in this map.
     * <p>
     * 返回此映射中键值映射的数量。
     *
     * @return the number of key-value mappings in this map
     */
    public int size() {
        return size;
    }

    /**
     * Returns <tt>true</tt> if this map contains no key-value mappings.
     * <p>
     * 如果此映射不包含键值映射，则返回 <tt>true</tt> 。
     *
     * @return <tt>true</tt> if this map contains no key-value mappings
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     * <p>
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     * <p>
     * <p>A return value of {@code null} does not <i>necessarily</i>
     * indicate that the map contains no mapping for the key; it's also
     * possible that the map explicitly maps the key to {@code null}.
     * The {@link #containsKey containsKey} operation may be used to
     * distinguish these two cases.
     * <p>
     * 返回指定键所映射的值；如果对于该键来说，此映射不包含任何映射关系，则返回 null。
     * 更确切地讲，如果此映射包含一个满足 (key==null ? k==null : key.equals(k)) 的从 k 键到 v 值的映射关系，则此方法返回 v；否则返回 null。（最多只能有一个这样的映射关系。）
     * <p>
     * 返回 null 值并不一定 表明该映射不包含该键的映射关系；也可能该映射将该键显示地映射为 null。可使用 containsKey 操作来区分这两种情况。
     *
     * @see #put(Object, Object)
     */
    public V get(Object key) {
        Node<K, V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }

    /**
     * Implements Map.get and related methods
     * <p>
     * 实现 Map.get 和相关方法
     *
     * @param hash hash for key
     * @param key  the key
     * @return the node, or null if none
     */
    final Node<K, V> getNode(int hash, Object key) {
        Node<K, V>[] tab;
        Node<K, V> first, e;
        int n;
        K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (first = tab[(n - 1) & hash]) != null) {
            /*
            Q:为什么是 first?
            first 是指的 hash 对应的 index 处的链表的 first
             */
            if (first.hash == hash && // always check first node
                    ((k = first.key) == key || (key != null && key.equals(k))))
                /*
                 *这里对 key 进行了两个判断 == 或者是 equals
                 * == 除了判断相等还可以判断 null
                 *
                 * Q: 为什么支持 null
                 * 因为取了 hash(key)，判断时也用了 == 判断，而 null 值也可以存在 Node 中
                 */

                return first;
            if ((e = first.next) != null) {
                if (first instanceof TreeNode)
                    //如果是树结点
                    return ((TreeNode<K, V>) first).getTreeNode(hash, key);
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the
     * specified key.
     * <p>
     * 如果此映射包含对于指定键的映射关系，则返回 true。
     *
     * @param key The key whose presence in this map is to be tested
     * @return <tt>true</tt> if this map contains a mapping for the specified
     * key.
     */
    public boolean containsKey(Object key) {
        //判断是否包含 key 也是要取一次 node 的
        // 这里返回 null 与 接受 null 值是不冲突的
        return getNode(hash(key), key) != null;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced.
     * <p>
     * 在此映射中关联指定值与指定键。如果该映射以前包含了一个该键的映射关系，则旧值被替换。
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     * <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * (A <tt>null</tt> return can also indicate that the map
     * previously associated <tt>null</tt> with <tt>key</tt>.)
     * <p>
     * 与 key 关联的旧值；如果 key 没有任何映射关系，则返回 null。
     * （返回 null 还可能表示该映射之前将 null 与 key 关联。）
     */
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    /**
     * Implements Map.put and related methods
     * 实现 Map.put 和相关的方法
     *
     * @param hash         hash for key
     *                     key 的 hash
     * @param key          the key
     * @param value        the value to put
     * @param onlyIfAbsent if true, don't change existing value
     *                     如果为 true,则不改变已存在的值
     * @param evict        if false, the table is in creation mode.
     *                     如果为 false，表格处于创建模式。
     * @return previous value, or null if none
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K, V>[] tab;
        Node<K, V> p;
        int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            //为空，进行 resize
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            /*
            Q:i 为什么等于 (n-1) & hash？
            可以使随机的 hash 均匀地分布于容量有限的 tab 中
             */
            //新的 node next 指向的是 null
            tab[i] = newNode(hash, key, value, null);
        else {
            //不等于 null ，此时 p 已赋值
            Node<K, V> e;
            K k;
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode)
                e = ((TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        //没有下一个，新建，新建的结点在 p 的后面
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            //树化
                            treeifyBin(tab, hash);
                        break;
                    }
                    //找到,返回
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                //存在
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    //没有配置 onlyIfAbsent 或者配置了,但旧值为 null
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }

    /**
     * Initializes or doubles table size.  If null, allocates in
     * accord with initial capacity target held in field threshold.
     * Otherwise, because we are using power-of-two expansion, the
     * elements from each bin must either stay at same index, or move
     * with a power of two offset in the new table.
     * <p>
     * 初始化或加倍表的大小。
     * 如果为空，则根据 threshold 中保存的阈值值对应的初始容量进行分配。
     * 否则若不为空，因为我们正在使用两次幂的扩展，所以每个桶中的元素都必须保持相同的索引，
     * 或者在新表中以 2 的幂的偏移量移动
     * <p>
     * 这里的偏移正好是旧桶的容量，见下文分析
     *
     * @return the table
     */
    final Node<K, V>[] resize() {
        Node<K, V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                    oldCap >= DEFAULT_INITIAL_CAPACITY)
                //这里的 oldCap >= 不成立可能发生于?
                //可能发生于通过构造函数设置的初始容量小于16，比如为 8，
                //1
                newThr = oldThr << 1; // double threshold
        } else if (oldThr > 0) // initial capacity was placed in threshold
            //已经配置了 threshold
            //2
            //带参数的两个构造函数
            // this.loadFactor = loadFactor;
            // this.threshold = tableSizeFor(initialCapacity);
            // 可以看到 threshold 并不是用来作为阈值，而是暂时存储初始容量
            newCap = oldThr;
        else {               // zero initial threshold signifies using defaults

            //无参构造函数，首次添加时会实始化，只设置了 this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            /*
            == 0 可能发生于上面的 1 或 2 中
            1 中对 newCap 的赋值在 if 中
             */
            float ft = (float) newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ?
                    (int) ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWarnings({"rawtypes", "unchecked"})
        Node<K, V>[] newTab = (Node<K, V>[]) new Node[newCap];
        table = newTab;
        if (oldTab != null) {
            for (int j = 0; j < oldCap; ++j) {
                Node<K, V> e;
                if ((e = oldTab[j]) != null) {
                    //置为 null
                    oldTab[j] = null;
                    if (e.next == null)
                        //单个
                        newTab[e.hash & (newCap - 1)] = e;
                    else if (e instanceof TreeNode)
                        ((TreeNode<K, V>) e).split(this, newTab, j, oldCap);
                    else { // preserve order
                        /*
                        Q:为什么要分开
                        如方法文档所说，桶中的所有元素，位置要么保持不变，要么进行偏移
                        使用下面的方法，不需要再计算 hash
                         */
                        Node<K, V> loHead = null, loTail = null;
                        Node<K, V> hiHead = null, hiTail = null;
                        Node<K, V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                // hash 不变的，保存在低部分
                                /*
                                Q:为什么是 e.hash & olcCap
                                oldCap 是 1 加一串 0，如果 & 上还是0，说明 hash 是不变的
                                为什么不变，比如
                                oldCap                  16  00010000
                                oldCap-1                15  00001111
                                newCap=olcCap<<1        32  00100000
                                newCap-1                31  00011111
                                如果 & oldCap == 0，说明最高位是0，
                                那么 & oldCap-1 与 & nexCap-1 的结果就相同，因为它们只是最高位不一样
                                 */
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            } else {
                                // hash 有变化的，保存在高部分
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            //低位的桶 index 不变，仍然是 j
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            //高位的桶 index 变为 j + oldCap
                            /*
                            Q: 为什么高部分的 index 直接就是 j+oldCap
                            因为原来的 index 是 j，根据上面的分析，如果要放到高位，是因为 hash 最高位是 1
                            那么 hash 出的 index 最高位就是 1，相比原来的 index ，多了 1 后面一串 0
                            这个 1 后面一串 0 正好是 oldCap
                             */
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }

    /**
     * Replaces all linked nodes in bin at index for given hash unless
     * table is too small, in which case resizes instead.
     * 替换给定 hash 对应的索引的桶中所有的链接节点，除非表太小，在这种情况下用 resize 代替。
     */
    final void treeifyBin(Node<K, V>[] tab, int hash) {
        int n, index;
        Node<K, V> e;
        if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
            resize();
        else if ((e = tab[index = (n - 1) & hash]) != null) {
            TreeNode<K, V> hd = null, tl = null;
            do {
                //将 Node 转为 TreeNode
                TreeNode<K, V> p = replacementTreeNode(e, null);
                if (tl == null)
                    hd = p;
                else {
                    p.prev = tl;
                    tl.next = p;
                }
                tl = p;
            } while ((e = e.next) != null);
            if ((tab[index] = hd) != null)
                // 上述处理后，如果不为空，则进行树化
                // 何时为空？replacementTreeNode 返回空吗）
                hd.treeify(tab);
        }
    }

    /**
     * Copies all of the mappings from the specified map to this map.
     * These mappings will replace any mappings that this map had for
     * any of the keys currently in the specified map.
     * <p>
     * 将指定映射的所有映射关系复制到此映射中，
     * 这些映射关系将替换此映射目前针对指定映射中所有键的所有映射关系。
     *
     * @param m mappings to be stored in this map
     * @throws NullPointerException if the specified map is null
     */
    public void putAll(Map<? extends K, ? extends V> m) {
        putMapEntries(m, true);
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * 从此映射中移除指定键的映射关系（如果存在）。
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with <tt>key</tt>, or
     * <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * (A <tt>null</tt> return can also indicate that the map
     * previously associated <tt>null</tt> with <tt>key</tt>.)
     * <p>
     * 与 key 关联的旧值；如果 key 没有任何映射关系，则返回 null。
     * （返回 null 还可能表示该映射之前将 null 与 key 关联。）
     */
    public V remove(Object key) {
        Node<K, V> e;
        return (e = removeNode(hash(key), key, null, false, true)) == null ?
                null : e.value;
    }

    /**
     * Implements Map.remove and related methods
     * 实现 Map.remove 和相关的方法
     *
     * @param hash       hash for key
     * @param key        the key
     * @param value      the value to match if matchValue, else ignored
     * @param matchValue if true only remove if value is equal
     *                   如果为真，则只有在值相等时才删除
     * @param movable    if false do not move other nodes while removing
     *                   如果为 false，则在移除时不要移动其他节点
     * @return the node, or null if none
     */
    final Node<K, V> removeNode(int hash, Object key, Object value,
                                boolean matchValue, boolean movable) {
        Node<K, V>[] tab;
        Node<K, V> p;
        int n, index;
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (p = tab[index = (n - 1) & hash]) != null) {
            // p 已取出对应的结点
            //找出是否有对应的结点
            Node<K, V> node = null, e;
            K k;
            V v;
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                node = p;
            else if ((e = p.next) != null) {
                if (p instanceof TreeNode)
                    node = ((TreeNode<K, V>) p).getTreeNode(hash, key);
                else {
                    do {
                        if (e.hash == hash &&
                                ((k = e.key) == key ||
                                        (key != null && key.equals(k)))) {
                            node = e;
                            break;
                        }
                        p = e;
                    } while ((e = e.next) != null);
                }
            }
            if (node != null && (!matchValue || (v = node.value) == value ||
                    (value != null && value.equals(v)))) {
                if (node instanceof TreeNode)
                    ((TreeNode<K, V>) node).removeTreeNode(this, tab, movable);
                else if (node == p)
                    //如果是第一个结点,直接置为 next (可能为 null)
                    tab[index] = node.next;
                else
                    p.next = node.next;
                ++modCount;
                --size;
                afterNodeRemoval(node);
                return node;
            }
        }
        return null;
    }

    /**
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     * 从此映射中移除所有映射关系。此调用返回后，映射将为空。
     */
    public void clear() {
        Node<K, V>[] tab;
        modCount++;
        if ((tab = table) != null && size > 0) {
            size = 0;
            for (int i = 0; i < tab.length; ++i)
                //简单粗暴
                tab[i] = null;
        }
    }

    /**
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.
     * 如果此映射将一个或多个键映射到指定值，则返回 true。
     *
     * @param value value whose presence in this map is to be tested
     * @return <tt>true</tt> if this map maps one or more keys to the
     * specified value
     */
    public boolean containsValue(Object value) {
        Node<K, V>[] tab;
        V v;
        if ((tab = table) != null && size > 0) {
            for (int i = 0; i < tab.length; ++i) {
                //可以看到判断 value 的时候，树结点并没有优化，依然是迭代判断
                for (Node<K, V> e = tab[i]; e != null; e = e.next) {
                    if ((v = e.value) == value ||
                            (value != null && value.equals(v)))
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a {@link Set} view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation), the results of
     * the iteration are undefined.  The set supports element removal,
     * which removes the corresponding mapping from the map, via the
     * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
     * operations.  It does not support the <tt>add</tt> or <tt>addAll</tt>
     * operations.
     * <p>
     * 返回此映射中所包含的键的 Set 视图。
     * 该 set 受映射的支持，所以对映射的更改将反映在该 set 中，反之亦然。
     * 如果在对 set 进行迭代的同时修改了映射（通过迭代器自己的 remove 操作除外），则迭代结果是不确定的。
     * 该 set 支持元素的移除，通过 Iterator.remove、Set.remove、removeAll、retainAll 和 clear 操作可从该映射中移除相应的映射关系。
     * 它不支持 add 或 addAll 操作。
     *
     * @return a set view of the keys contained in this map
     */
    public Set<K> keySet() {
        Set<K> ks = keySet;
        if (ks == null) {
            ks = new KeySet();
            keySet = ks;
        }
        return ks;
    }

    final class KeySet extends AbstractSet<K> {
        public final int size() {
            return size;
        }

        public final void clear() {
            HashMap.this.clear();
        }

        public final Iterator<K> iterator() {
            return new KeyIterator();
        }

        public final boolean contains(Object o) {
            //因为是 key，所以调用的 containsKey 方法
            return containsKey(o);
        }

        public final boolean remove(Object key) {
            //没有指定 value 但是传了 matchValue 为 false
            return removeNode(hash(key), key, null, false, true) != null;
        }

        public final Spliterator<K> spliterator() {
            return new KeySpliterator<>(HashMap.this, 0, -1, 0, 0);
        }

        public final void forEach(Consumer<? super K> action) {
            Node<K, V>[] tab;
            if (action == null)
                throw new NullPointerException();
            if (size > 0 && (tab = table) != null) {
                int mc = modCount;
                for (int i = 0; i < tab.length; ++i) {
                    for (Node<K, V> e = tab[i]; e != null; e = e.next)
                        action.accept(e.key);
                }
                if (modCount != mc)
                    throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * Returns a {@link Collection} view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are
     * reflected in the collection, and vice-versa.  If the map is
     * modified while an iteration over the collection is in progress
     * (except through the iterator's own <tt>remove</tt> operation),
     * the results of the iteration are undefined.  The collection
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not
     * support the <tt>add</tt> or <tt>addAll</tt> operations.
     * <p>
     * 返回此映射所包含的值的 Collection 视图。
     * 该 collection 受映射的支持，所以对映射的更改将反映在该 collection 中，反之亦然。
     * 如果在对 collection 进行迭代的同时修改了映射（通过迭代器自己的 remove 操作除外），则迭代结果是不确定的。
     * 该 collection 支持元素的移除，通过 Iterator.remove、Collection.remove、removeAll、retainAll 和 clear 操作可从该映射中移除相应的映射关系。
     * 它不支持 add 或 addAll 操作。
     *
     * @return a view of the values contained in this map
     */
    public Collection<V> values() {
        Collection<V> vs = values;
        if (vs == null) {
            vs = new Values();
            values = vs;
        }
        return vs;
    }

    final class Values extends AbstractCollection<V> {
        public final int size() {
            return size;
        }

        public final void clear() {
            HashMap.this.clear();
        }

        public final Iterator<V> iterator() {
            return new ValueIterator();
        }

        public final boolean contains(Object o) {
            // 与 keySet 对应，该方法调用的是 containsValue
            return containsValue(o);
        }

        public final Spliterator<V> spliterator() {
            return new ValueSpliterator<>(HashMap.this, 0, -1, 0, 0);
        }

        public final void forEach(Consumer<? super V> action) {
            Node<K, V>[] tab;
            if (action == null)
                throw new NullPointerException();
            if (size > 0 && (tab = table) != null) {
                int mc = modCount;
                for (int i = 0; i < tab.length; ++i) {
                    for (Node<K, V> e = tab[i]; e != null; e = e.next)
                        action.accept(e.value);
                }
                if (modCount != mc)
                    throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * Returns a {@link Set} view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation, or through the
     * <tt>setValue</tt> operation on a map entry returned by the
     * iterator) the results of the iteration are undefined.  The set
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
     * <tt>clear</tt> operations.  It does not support the
     * <tt>add</tt> or <tt>addAll</tt> operations.
     * <p>
     * 返回此映射所包含的映射关系的 Set 视图。
     * 该 set 受映射支持，所以对映射的更改将反映在此 set 中，反之亦然。
     * 如果在对 set 进行迭代的同时修改了映射（通过迭代器自己的 remove 操作，或者通过在该迭代器返回的映射项上执行 setValue 操作除外），则迭代结果是不确定的。
     * 该 set 支持元素的移除，通过 Iterator.remove、Set.remove、removeAll、retainAll 和 clear 操作可从该映射中移除相应的映射关系。
     * 它不支持 add 或 addAll 操作。
     *
     * @return a set view of the mappings contained in this map
     */
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> es;
        return (es = entrySet) == null ? (entrySet = new EntrySet()) : es;
    }

    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        public final int size() {
            return size;
        }

        public final void clear() {
            HashMap.this.clear();
        }

        public final Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        public final boolean contains(Object o) {
            // 相比 keySet 和 values ， EntrySet 要判断的比较多，必须取出结点判断 key value 都相等
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
            Object key = e.getKey();
            Node<K, V> candidate = getNode(hash(key), key);
            return candidate != null && candidate.equals(e);
        }

        public final boolean remove(Object o) {
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
                Object key = e.getKey();
                Object value = e.getValue();
                // matchValue 传的 true
                return removeNode(hash(key), key, value, true, true) != null;
            }
            return false;
        }

        public final Spliterator<Map.Entry<K, V>> spliterator() {
            return new EntrySpliterator<>(HashMap.this, 0, -1, 0, 0);
        }

        public final void forEach(Consumer<? super Map.Entry<K, V>> action) {
            Node<K, V>[] tab;
            if (action == null)
                throw new NullPointerException();
            if (size > 0 && (tab = table) != null) {
                int mc = modCount;
                for (int i = 0; i < tab.length; ++i) {
                    for (Node<K, V> e = tab[i]; e != null; e = e.next)
                        action.accept(e);
                }
                if (modCount != mc)
                    throw new ConcurrentModificationException();
            }
        }
    }

    // Overrides of JDK8 Map extension methods
    // 覆盖 JDK8 Map 的扩展方法

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        Node<K, V> e;
        return (e = getNode(hash(key), key)) == null ? defaultValue : e.value;
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return putVal(hash(key), key, value, true, true);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return removeNode(hash(key), key, value, true, true) != null;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        Node<K, V> e;
        V v;
        if ((e = getNode(hash(key), key)) != null &&
                ((v = e.value) == oldValue || (v != null && v.equals(oldValue)))) {
            e.value = newValue;
            afterNodeAccess(e);
            return true;
        }
        return false;
    }

    @Override
    public V replace(K key, V value) {
        Node<K, V> e;
        if ((e = getNode(hash(key), key)) != null) {
            V oldValue = e.value;
            e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
        return null;
    }

    @Override
    public V computeIfAbsent(K key,
                             Function<? super K, ? extends V> mappingFunction) {
        if (mappingFunction == null)
            throw new NullPointerException();
        int hash = hash(key);
        Node<K, V>[] tab;
        Node<K, V> first;
        int n, i;
        int binCount = 0;
        TreeNode<K, V> t = null;
        Node<K, V> old = null;
        if (size > threshold || (tab = table) == null ||
                (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((first = tab[i = (n - 1) & hash]) != null) {
            if (first instanceof TreeNode)
                old = (t = (TreeNode<K, V>) first).getTreeNode(hash, key);
            else {
                Node<K, V> e = first;
                K k;
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k)))) {
                        old = e;
                        break;
                    }
                    ++binCount;
                } while ((e = e.next) != null);
            }
            V oldValue;
            if (old != null && (oldValue = old.value) != null) {
                afterNodeAccess(old);
                return oldValue;
            }
        }
        V v = mappingFunction.apply(key);
        if (v == null) {
            return null;
        } else if (old != null) {
            old.value = v;
            afterNodeAccess(old);
            return v;
        } else if (t != null)
            t.putTreeVal(this, tab, hash, key, v);
        else {
            tab[i] = newNode(hash, key, v, first);
            if (binCount >= TREEIFY_THRESHOLD - 1)
                treeifyBin(tab, hash);
        }
        ++modCount;
        ++size;
        afterNodeInsertion(true);
        return v;
    }

    public V computeIfPresent(K key,
                              BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        if (remappingFunction == null)
            throw new NullPointerException();
        Node<K, V> e;
        V oldValue;
        int hash = hash(key);
        if ((e = getNode(hash, key)) != null &&
                (oldValue = e.value) != null) {
            V v = remappingFunction.apply(key, oldValue);
            if (v != null) {
                e.value = v;
                afterNodeAccess(e);
                return v;
            } else
                removeNode(hash, key, null, false, true);
        }
        return null;
    }

    @Override
    public V compute(K key,
                     BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        if (remappingFunction == null)
            throw new NullPointerException();
        int hash = hash(key);
        Node<K, V>[] tab;
        Node<K, V> first;
        int n, i;
        int binCount = 0;
        TreeNode<K, V> t = null;
        Node<K, V> old = null;
        if (size > threshold || (tab = table) == null ||
                (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((first = tab[i = (n - 1) & hash]) != null) {
            if (first instanceof TreeNode)
                old = (t = (TreeNode<K, V>) first).getTreeNode(hash, key);
            else {
                Node<K, V> e = first;
                K k;
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k)))) {
                        old = e;
                        break;
                    }
                    ++binCount;
                } while ((e = e.next) != null);
            }
        }
        V oldValue = (old == null) ? null : old.value;
        V v = remappingFunction.apply(key, oldValue);
        if (old != null) {
            if (v != null) {
                old.value = v;
                afterNodeAccess(old);
            } else
                removeNode(hash, key, null, false, true);
        } else if (v != null) {
            if (t != null)
                t.putTreeVal(this, tab, hash, key, v);
            else {
                tab[i] = newNode(hash, key, v, first);
                if (binCount >= TREEIFY_THRESHOLD - 1)
                    treeifyBin(tab, hash);
            }
            ++modCount;
            ++size;
            afterNodeInsertion(true);
        }
        return v;
    }

    @Override
    public V merge(K key, V value,
                   BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        if (value == null)
            throw new NullPointerException();
        if (remappingFunction == null)
            throw new NullPointerException();
        int hash = hash(key);
        Node<K, V>[] tab;
        Node<K, V> first;
        int n, i;
        int binCount = 0;
        TreeNode<K, V> t = null;
        Node<K, V> old = null;
        if (size > threshold || (tab = table) == null ||
                (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((first = tab[i = (n - 1) & hash]) != null) {
            if (first instanceof TreeNode)
                old = (t = (TreeNode<K, V>) first).getTreeNode(hash, key);
            else {
                Node<K, V> e = first;
                K k;
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k)))) {
                        old = e;
                        break;
                    }
                    ++binCount;
                } while ((e = e.next) != null);
            }
        }
        if (old != null) {
            V v;
            if (old.value != null)
                v = remappingFunction.apply(old.value, value);
            else
                v = value;
            if (v != null) {
                old.value = v;
                afterNodeAccess(old);
            } else
                removeNode(hash, key, null, false, true);
            return v;
        }
        if (value != null) {
            if (t != null)
                t.putTreeVal(this, tab, hash, key, value);
            else {
                tab[i] = newNode(hash, key, value, first);
                if (binCount >= TREEIFY_THRESHOLD - 1)
                    treeifyBin(tab, hash);
            }
            ++modCount;
            ++size;
            afterNodeInsertion(true);
        }
        return value;
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Node<K, V>[] tab;
        if (action == null)
            throw new NullPointerException();
        if (size > 0 && (tab = table) != null) {
            int mc = modCount;
            for (int i = 0; i < tab.length; ++i) {
                for (Node<K, V> e = tab[i]; e != null; e = e.next)
                    action.accept(e.key, e.value);
            }
            if (modCount != mc)
                throw new ConcurrentModificationException();
        }
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Node<K, V>[] tab;
        if (function == null)
            throw new NullPointerException();
        if (size > 0 && (tab = table) != null) {
            int mc = modCount;
            for (int i = 0; i < tab.length; ++i) {
                for (Node<K, V> e = tab[i]; e != null; e = e.next) {
                    e.value = function.apply(e.key, e.value);
                }
            }
            if (modCount != mc)
                throw new ConcurrentModificationException();
        }
    }

    /* ------------------------------------------------------------ */
    // Cloning and serialization
    // 克隆和序列化

    /**
     * Returns a shallow copy of this <tt>HashMap</tt> instance: the keys and
     * values themselves are not cloned.
     * 返回此 HashMap 实例的浅表副本：并不复制键和值本身。
     *
     * @return a shallow copy of this map
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object clone() {
        HashMap<K, V> result;
        try {
            result = (HashMap<K, V>) super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
        result.reinitialize();
        result.putMapEntries(this, false);
        return result;
    }

    // These methods are also used when serializing HashSets
    // 序列化 HashSets 时也使用这些方法
    final float loadFactor() {
        return loadFactor;
    }

    final int capacity() {
        return (table != null) ? table.length :
                (threshold > 0) ? threshold :
                        DEFAULT_INITIAL_CAPACITY;
    }

    /**
     * Save the state of the <tt>HashMap</tt> instance to a stream (i.e.,
     * serialize it).
     *
     * @serialData The <i>capacity</i> of the HashMap (the length of the
     * bucket array) is emitted (int), followed by the
     * <i>size</i> (an int, the number of key-value
     * mappings), followed by the key (Object) and value (Object)
     * for each key-value mapping.  The key-value mappings are
     * emitted in no particular order.
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws IOException {
        int buckets = capacity();
        // Write out the threshold, loadfactor, and any hidden stuff
        s.defaultWriteObject();
        s.writeInt(buckets);
        s.writeInt(size);
        internalWriteEntries(s);
    }

    /**
     * Reconstitute the {@code HashMap} instance from a stream (i.e.,
     * deserialize it).
     */
    private void readObject(java.io.ObjectInputStream s)
            throws IOException, ClassNotFoundException {
        // Read in the threshold (ignored), loadfactor, and any hidden stuff
        s.defaultReadObject();
        reinitialize();
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new InvalidObjectException("Illegal load factor: " +
                    loadFactor);
        s.readInt();                // Read and ignore number of buckets
        int mappings = s.readInt(); // Read number of mappings (size)
        if (mappings < 0)
            throw new InvalidObjectException("Illegal mappings count: " +
                    mappings);
        else if (mappings > 0) { // (if zero, use defaults)
            // Size the table using given load factor only if within
            // range of 0.25...4.0
            float lf = Math.min(Math.max(0.25f, loadFactor), 4.0f);
            float fc = (float) mappings / lf + 1.0f;
            int cap = ((fc < DEFAULT_INITIAL_CAPACITY) ?
                    DEFAULT_INITIAL_CAPACITY :
                    (fc >= MAXIMUM_CAPACITY) ?
                            MAXIMUM_CAPACITY :
                            tableSizeFor((int) fc));
            float ft = (float) cap * lf;
            threshold = ((cap < MAXIMUM_CAPACITY && ft < MAXIMUM_CAPACITY) ?
                    (int) ft : Integer.MAX_VALUE);

            // Check Map.Entry[].class since it's the nearest public type to
            // what we're actually creating.
            SharedSecrets.getJavaOISAccess().checkArray(s, Map.Entry[].class, cap);
            @SuppressWarnings({"rawtypes", "unchecked"})
            Node<K, V>[] tab = (Node<K, V>[]) new Node[cap];
            table = tab;

            // Read the keys and values, and put the mappings in the HashMap
            for (int i = 0; i < mappings; i++) {
                @SuppressWarnings("unchecked")
                K key = (K) s.readObject();
                @SuppressWarnings("unchecked")
                V value = (V) s.readObject();
                putVal(hash(key), key, value, false, false);
            }
        }
    }

    /* ------------------------------------------------------------ */
    // iterators

    abstract class HashIterator {
        Node<K, V> next;        // next entry to return
        Node<K, V> current;     // current entry
        int expectedModCount;  // for fast-fail
        int index;             // current slot

        HashIterator() {
            expectedModCount = modCount;
            Node<K, V>[] t = table;
            current = next = null;
            index = 0;
            if (t != null && size > 0) { // advance to first entry
                /*
                前进到第一个 entry
                如果第 0 个就不为 null，则 index 也会置为 1 的
                 */
                do {
                } while (index < t.length && (next = t[index++]) == null);
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        final Node<K, V> nextNode() {
            Node<K, V>[] t;
            Node<K, V> e = next;
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (e == null)
                throw new NoSuchElementException();
            if ((next = (current = e).next) == null && (t = table) != null) {
                /*
                可以看到 next 取的是 index++ 处的元素
                这也就是为什么 HashMap 是无序的
                 */
                /*
                Q:collection 视图迭代所需时间
                在这里可以看到，在迭代时，如果 current 有 next ，说明它的 next 在链表（或树中）
                在这种情况下，直接赋值 next 供下一次迭代，而 index 不变
                它的迭代与映射大小有关

                但如果 next 为 null ，则需要根据 index 遍历整个 table ，也就是说即使 index 处没有节点，
                仍然需要取出来判断，因此视图迭代与容量有关，
                与 LinkedHashMap 区分开，它只迭代节点，因此只与映射大小有关
                 */
                do {
                } while (index < t.length && (next = t[index++]) == null);
            }
            return e;
        }

        public final void remove() {
            Node<K, V> p = current;
            if (p == null)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            current = null;
            K key = p.key;
            removeNode(hash(key), key, null, false, false);
            expectedModCount = modCount;
        }
    }

    final class KeyIterator extends HashIterator
            implements Iterator<K> {
        //可以看到 3 个 Iterator 都继承自 HashIterator
        public final K next() {
            return nextNode().key;
        }
    }

    final class ValueIterator extends HashIterator
            implements Iterator<V> {
        public final V next() {
            return nextNode().value;
        }
    }

    final class EntryIterator extends HashIterator
            implements Iterator<Map.Entry<K, V>> {
        public final Map.Entry<K, V> next() {
            return nextNode();
        }
    }

    /* ------------------------------------------------------------ */
    // spliterators

    static class HashMapSpliterator<K, V> {
        final HashMap<K, V> map;
        Node<K, V> current;          // current node
        int index;                  // current index, modified on advance/split
        int fence;                  // one past last index
        int est;                    // size estimate
        int expectedModCount;       // for comodification checks

        HashMapSpliterator(HashMap<K, V> m, int origin,
                           int fence, int est,
                           int expectedModCount) {
            this.map = m;
            this.index = origin;
            this.fence = fence;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        final int getFence() { // initialize fence and size on first use
            int hi;
            if ((hi = fence) < 0) {
                HashMap<K, V> m = map;
                est = m.size;
                expectedModCount = m.modCount;
                Node<K, V>[] tab = m.table;
                hi = fence = (tab == null) ? 0 : tab.length;
            }
            return hi;
        }

        public final long estimateSize() {
            getFence(); // force init
            return (long) est;
        }
    }

    static final class KeySpliterator<K, V>
            extends HashMapSpliterator<K, V>
            implements Spliterator<K> {
        KeySpliterator(HashMap<K, V> m, int origin, int fence, int est,
                       int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public KeySpliterator<K, V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid || current != null) ? null :
                    new KeySpliterator<>(map, lo, index = mid, est >>>= 1,
                            expectedModCount);
        }

        public void forEachRemaining(Consumer<? super K> action) {
            int i, hi, mc;
            if (action == null)
                throw new NullPointerException();
            HashMap<K, V> m = map;
            Node<K, V>[] tab = m.table;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = (tab == null) ? 0 : tab.length;
            } else
                mc = expectedModCount;
            if (tab != null && tab.length >= hi &&
                    (i = index) >= 0 && (i < (index = hi) || current != null)) {
                Node<K, V> p = current;
                current = null;
                do {
                    if (p == null)
                        p = tab[i++];
                    else {
                        action.accept(p.key);
                        p = p.next;
                    }
                } while (p != null || i < hi);
                if (m.modCount != mc)
                    throw new ConcurrentModificationException();
            }
        }

        public boolean tryAdvance(Consumer<? super K> action) {
            int hi;
            if (action == null)
                throw new NullPointerException();
            Node<K, V>[] tab = map.table;
            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null)
                        current = tab[index++];
                    else {
                        K k = current.key;
                        current = current.next;
                        action.accept(k);
                        if (map.modCount != expectedModCount)
                            throw new ConcurrentModificationException();
                        return true;
                    }
                }
            }
            return false;
        }

        public int characteristics() {
            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0) |
                    Spliterator.DISTINCT;
        }
    }

    static final class ValueSpliterator<K, V>
            extends HashMapSpliterator<K, V>
            implements Spliterator<V> {
        ValueSpliterator(HashMap<K, V> m, int origin, int fence, int est,
                         int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public ValueSpliterator<K, V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid || current != null) ? null :
                    new ValueSpliterator<>(map, lo, index = mid, est >>>= 1,
                            expectedModCount);
        }

        public void forEachRemaining(Consumer<? super V> action) {
            int i, hi, mc;
            if (action == null)
                throw new NullPointerException();
            HashMap<K, V> m = map;
            Node<K, V>[] tab = m.table;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = (tab == null) ? 0 : tab.length;
            } else
                mc = expectedModCount;
            if (tab != null && tab.length >= hi &&
                    (i = index) >= 0 && (i < (index = hi) || current != null)) {
                Node<K, V> p = current;
                current = null;
                do {
                    if (p == null)
                        p = tab[i++];
                    else {
                        action.accept(p.value);
                        p = p.next;
                    }
                } while (p != null || i < hi);
                if (m.modCount != mc)
                    throw new ConcurrentModificationException();
            }
        }

        public boolean tryAdvance(Consumer<? super V> action) {
            int hi;
            if (action == null)
                throw new NullPointerException();
            Node<K, V>[] tab = map.table;
            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null)
                        current = tab[index++];
                    else {
                        V v = current.value;
                        current = current.next;
                        action.accept(v);
                        if (map.modCount != expectedModCount)
                            throw new ConcurrentModificationException();
                        return true;
                    }
                }
            }
            return false;
        }

        public int characteristics() {
            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0);
        }
    }

    static final class EntrySpliterator<K, V>
            extends HashMapSpliterator<K, V>
            implements Spliterator<Map.Entry<K, V>> {
        EntrySpliterator(HashMap<K, V> m, int origin, int fence, int est,
                         int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public EntrySpliterator<K, V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid || current != null) ? null :
                    new EntrySpliterator<>(map, lo, index = mid, est >>>= 1,
                            expectedModCount);
        }

        public void forEachRemaining(Consumer<? super Map.Entry<K, V>> action) {
            int i, hi, mc;
            if (action == null)
                throw new NullPointerException();
            HashMap<K, V> m = map;
            Node<K, V>[] tab = m.table;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = (tab == null) ? 0 : tab.length;
            } else
                mc = expectedModCount;
            if (tab != null && tab.length >= hi &&
                    (i = index) >= 0 && (i < (index = hi) || current != null)) {
                Node<K, V> p = current;
                current = null;
                do {
                    if (p == null)
                        p = tab[i++];
                    else {
                        action.accept(p);
                        p = p.next;
                    }
                } while (p != null || i < hi);
                if (m.modCount != mc)
                    throw new ConcurrentModificationException();
            }
        }

        public boolean tryAdvance(Consumer<? super Map.Entry<K, V>> action) {
            int hi;
            if (action == null)
                throw new NullPointerException();
            Node<K, V>[] tab = map.table;
            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null)
                        current = tab[index++];
                    else {
                        Node<K, V> e = current;
                        current = current.next;
                        action.accept(e);
                        if (map.modCount != expectedModCount)
                            throw new ConcurrentModificationException();
                        return true;
                    }
                }
            }
            return false;
        }

        public int characteristics() {
            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0) |
                    Spliterator.DISTINCT;
        }
    }

    /* ------------------------------------------------------------ */
    // LinkedHashMap support


    /*
     * The following package-protected methods are designed to be
     * overridden by LinkedHashMap, but not by any other subclass.
     * Nearly all other internal methods are also package-protected
     * but are declared final, so can be used by LinkedHashMap, view
     * classes, and HashSet.
     *
     * 下面的包保护方法被设计为被 LinkedHashMap 覆盖，但不被其他子类覆盖。
     * 几乎所有其他的内部方法也被封装保护，但被声明为 final，因此可以被 LinkedHashMap，视图类和 HashSet 使用。
     */

    // Create a regular (non-tree) node
    Node<K, V> newNode(int hash, K key, V value, Node<K, V> next) {
        return new Node<>(hash, key, value, next);
    }

    // For conversion from TreeNodes to plain nodes
    Node<K, V> replacementNode(Node<K, V> p, Node<K, V> next) {
        return new Node<>(p.hash, p.key, p.value, next);
    }

    // Create a tree bin node
    TreeNode<K, V> newTreeNode(int hash, K key, V value, Node<K, V> next) {
        return new TreeNode<>(hash, key, value, next);
    }

    // For treeifyBin
    TreeNode<K, V> replacementTreeNode(Node<K, V> p, Node<K, V> next) {
        return new TreeNode<>(p.hash, p.key, p.value, next);
    }

    /**
     * Reset to initial default state.  Called by clone and readObject.
     */
    void reinitialize() {
        table = null;
        entrySet = null;
        keySet = null;
        values = null;
        modCount = 0;
        threshold = 0;
        size = 0;
    }

    // Callbacks to allow LinkedHashMap post-actions
    void afterNodeAccess(Node<K, V> p) {
    }

    void afterNodeInsertion(boolean evict) {
    }

    void afterNodeRemoval(Node<K, V> p) {
    }

    // Called only from writeObject, to ensure compatible ordering.
    void internalWriteEntries(java.io.ObjectOutputStream s) throws IOException {
        Node<K, V>[] tab;
        if (size > 0 && (tab = table) != null) {
            for (int i = 0; i < tab.length; ++i) {
                for (Node<K, V> e = tab[i]; e != null; e = e.next) {
                    s.writeObject(e.key);
                    s.writeObject(e.value);
                }
            }
        }
    }

    /* ------------------------------------------------------------ */
    // Tree bins

    /**
     * Entry for Tree bins. Extends LinkedHashMap.Entry (which in turn
     * extends Node) so can be used as extension of either regular or
     * linked node.
     * Tree bins 的 entry 。 扩展 LinkedHashMap.Entry（进而扩展 Node），因此可以用作常规节点或链接节点的扩展。
     */
    static final class TreeNode<K, V> extends LinkedHashMap.Entry<K, V> {
        // TODO: 2018/3/12 红黑树
        TreeNode<K, V> parent;  // red-black tree links
        TreeNode<K, V> left;
        TreeNode<K, V> right;
        TreeNode<K, V> prev;    // needed to unlink next upon deletion
        boolean red;

        TreeNode(int hash, K key, V val, Node<K, V> next) {
            super(hash, key, val, next);
        }

        /**
         * Returns root of tree containing this node.
         * 返回包含此节点的树的根。
         */
        final TreeNode<K, V> root() {
            for (TreeNode<K, V> r = this, p; ; ) {
                if ((p = r.parent) == null)
                    return r;
                r = p;
            }
        }

        /**
         * Ensures that the given root is the first node of its bin.
         * 确保给定的根是它的 bin 的第一个节点。
         */
        static <K, V> void moveRootToFront(Node<K, V>[] tab, TreeNode<K, V> root) {
            int n;
            if (root != null && tab != null && (n = tab.length) > 0) {
                int index = (n - 1) & root.hash;
                TreeNode<K, V> first = (TreeNode<K, V>) tab[index];
                if (root != first) {
                    Node<K, V> rn;
                    tab[index] = root;
                    TreeNode<K, V> rp = root.prev;
                    // root 提到最前后，它的前后结点连到一起
                    if ((rn = root.next) != null)
                        // root 要提前,将 root.next 的 prev 设为 root.prev
                        ((TreeNode<K, V>) rn).prev = rp;
                    if (rp != null)
                        //root.prev 的 next 设为 root.next
                        rp.next = rn;
                    // 将之前的第一个 first 拼到 root 后面
                    if (first != null)
                        first.prev = root;
                    root.next = first;
                    root.prev = null;
                }
                assert checkInvariants(root);
            }
        }

        /**
         * Finds the node starting at root p with the given hash and key.
         * The kc argument caches comparableClassFor(key) upon first use
         * comparing keys.
         * 使用给定的 hash 和 key 查找从根 p 开始的节点。
         * kc 参数在第一次使用比较键时缓存 comparableClassFor(key)。
         */
        final TreeNode<K, V> find(int h, Object k, Class<?> kc) {
            TreeNode<K, V> p = this;
            do {
                int ph, dir;
                K pk;
                TreeNode<K, V> pl = p.left, pr = p.right, q;
                if ((ph = p.hash) > h)
                    //往左找
                    p = pl;
                else if (ph < h)
                    //往右找
                    p = pr;
                else if ((pk = p.key) == k || (k != null && k.equals(pk)))
                    //后面的 else if 都是在 hash 相等的前提下的
                    //hash 相等，key 相同，找到，返回
                    return p;
                else if (pl == null)
                    // key 不同，左边为 null ，继续往右
                    p = pr;
                else if (pr == null)
                    //往左
                    p = pl;
                else if ((kc != null ||
                        (kc = comparableClassFor(k)) != null) &&
                        (dir = compareComparables(kc, k, pk)) != 0)
                    // key 不同,但是可以根据 comparable 判断
                    p = (dir < 0) ? pl : pr;
                else if ((q = pr.find(h, k, kc)) != null)
                    //如果没有只好在右边查找
                    return q;
                else
                    //如果右边没找到,在左边找
                    p = pl;
            } while (p != null);
            return null;
        }

        /**
         * Calls find for root node.
         * 对根节点调用 find 。
         */
        final TreeNode<K, V> getTreeNode(int h, Object k) {
            //先求出根结点,然后调用 find 方法
            return ((parent != null) ? root() : this).find(h, k, null);
        }

        /**
         * Tie-breaking utility for ordering insertions when equal
         * hashCodes and non-comparable. We don't require a total
         * order, just a consistent insertion rule to maintain
         * equivalence across rebalancings. Tie-breaking further than
         * necessary simplifies testing a bit.
         * <p>
         * 当相同的 hashCodes 非不可比较的时候，用于排序插入的打断实用方法。
         * 我们不需要全部排序，只需要一个一致的插入规则来维护重新包装之间的等价关系。
         * 进一步超越必要的联系简化了测试。
         */
        static int tieBreakOrder(Object a, Object b) {
            int d;
            if (a == null || b == null ||
                    (d = a.getClass().getName().
                            compareTo(b.getClass().getName())) == 0)
                d = (System.identityHashCode(a) <= System.identityHashCode(b) ?
                        -1 : 1);
            return d;
        }

        /**
         * Forms tree of the nodes linked from this node.
         * 将从此节点链接的节点转为树形式。
         *
         * @return root of tree
         */
        final void treeify(Node<K, V>[] tab) {
            TreeNode<K, V> root = null;
            for (TreeNode<K, V> x = this, next; x != null; x = next) {
                next = (TreeNode<K, V>) x.next;
                x.left = x.right = null;
                if (root == null) {
                    //设置根节点
                    x.parent = null;
                    x.red = false;
                    root = x;
                } else {
                    K k = x.key;
                    int h = x.hash;
                    Class<?> kc = null;
                    for (TreeNode<K, V> p = root; ; ) {
                        int dir, ph;
                        K pk = p.key;
                        if ((ph = p.hash) > h)
                            dir = -1;
                        else if (ph < h)
                            dir = 1;
                        else if ((kc == null &&
                                (kc = comparableClassFor(k)) == null) ||
                                (dir = compareComparables(kc, k, pk)) == 0)
                            dir = tieBreakOrder(k, pk);
                        //上述代码求出 dir 以确定往左还是往右
                        TreeNode<K, V> xp = p;
                        if ((p = (dir <= 0) ? p.left : p.right) == null) {
                            //当为 null 时,拼接上去,否则继续
                            x.parent = xp;
                            if (dir <= 0)
                                xp.left = x;
                            else
                                xp.right = x;
                            root = balanceInsertion(root, x);
                            break;
                        }
                    }
                }
            }
            moveRootToFront(tab, root);
        }

        /**
         * Returns a list of non-TreeNodes replacing those linked from
         * this node.
         * 返回非 TreeNodes 的列表，替换从此节点链接的列表。
         */
        final Node<K, V> untreeify(HashMap<K, V> map) {
            Node<K, V> hd = null, tl = null;
            for (Node<K, V> q = this; q != null; q = q.next) {
                Node<K, V> p = map.replacementNode(q, null);
                //遍历重新连接
                if (tl == null)
                    hd = p;
                else
                    tl.next = p;
                tl = p;
            }
            return hd;
        }

        /**
         * Tree version of putVal.
         * 树版本的 putVal .
         *
         * @param h hash
         */
        final TreeNode<K, V> putTreeVal(HashMap<K, V> map, Node<K, V>[] tab,
                                        int h, K k, V v) {
            Class<?> kc = null;
            boolean searched = false;
            TreeNode<K, V> root = (parent != null) ? root() : this;
            for (TreeNode<K, V> p = root; ; ) {
                int dir, ph;
                K pk;
                if ((ph = p.hash) > h)
                    //p 的 hash 比 h 大,目标在左边
                    dir = -1;
                else if (ph < h)
                    //目标在右边
                    dir = 1;
                else if ((pk = p.key) == k || (k != null && k.equals(pk)))
                    // hash 相等，key 相等，返回 p
                    return p;
                else if ((kc == null &&
                        (kc = comparableClassFor(k)) == null) ||
                        (dir = compareComparables(kc, k, pk)) == 0) {
                    if (!searched) {
                        TreeNode<K, V> q, ch;
                        searched = true;
                        if (((ch = p.left) != null &&
                                (q = ch.find(h, k, kc)) != null) ||
                                ((ch = p.right) != null &&
                                        (q = ch.find(h, k, kc)) != null))
                            //存在直接返回,上层调用方法会赋值
                            return q;
                    }
                    dir = tieBreakOrder(k, pk);
                }

                TreeNode<K, V> xp = p;
                if ((p = (dir <= 0) ? p.left : p.right) == null) {
                    //如果没有所查找方向的子结点，说明没有找到目标，新建一个树结点
                    Node<K, V> xpn = xp.next;
                    TreeNode<K, V> x = map.newTreeNode(h, k, v, xpn);
                    if (dir <= 0)
                        xp.left = x;
                    else
                        xp.right = x;
                    xp.next = x;
                    x.parent = x.prev = xp;
                    if (xpn != null)
                        ((TreeNode<K, V>) xpn).prev = x;
                    moveRootToFront(tab, balanceInsertion(root, x));
                    return null;
                }
            }
        }

        /**
         * Removes the given node, that must be present before this call.
         * This is messier than typical red-black deletion code because we
         * cannot swap the contents of an interior node with a leaf
         * successor that is pinned by "next" pointers that are accessible
         * independently during traversal. So instead we swap the tree
         * linkages. If the current tree appears to have too few nodes,
         * the bin is converted back to a plain bin. (The test triggers
         * somewhere between 2 and 6 nodes, depending on tree structure).
         * <p>
         * 删除给定的节点，该节点必须在此调用之前存在。
         * 这比典型的红黑删除代码更混乱，因为我们不能将内部节点的内容与被遍历期间独立访问的“下一个”指针固定的叶子后继替换。
         * 所以相反，我们交换树链接。
         * 如果当前树看起来节点太少，则该箱将转换回普通箱。
         * （测试触发2到6个节点之间，取决于树结构）。
         */
        final void removeTreeNode(HashMap<K, V> map, Node<K, V>[] tab,
                                  boolean movable) {
            int n;
            if (tab == null || (n = tab.length) == 0)
                return;
            int index = (n - 1) & hash;
            TreeNode<K, V> first = (TreeNode<K, V>) tab[index], root = first, rl;
            TreeNode<K, V> succ = (TreeNode<K, V>) next, pred = prev;
            if (pred == null)
                tab[index] = first = succ;
            else
                pred.next = succ;
            if (succ != null)
                succ.prev = pred;
            if (first == null)
                return;
            if (root.parent != null)
                root = root.root();
            if (root == null || root.right == null ||
                    (rl = root.left) == null || rl.left == null) {
                tab[index] = first.untreeify(map);  // too small
                return;
            }
            TreeNode<K, V> p = this, pl = left, pr = right, replacement;
            if (pl != null && pr != null) {
                TreeNode<K, V> s = pr, sl;
                while ((sl = s.left) != null) // find successor
                    s = sl;
                boolean c = s.red;
                s.red = p.red;
                p.red = c; // swap colors
                TreeNode<K, V> sr = s.right;
                TreeNode<K, V> pp = p.parent;
                if (s == pr) { // p was s's direct parent
                    p.parent = s;
                    s.right = p;
                } else {
                    TreeNode<K, V> sp = s.parent;
                    if ((p.parent = sp) != null) {
                        if (s == sp.left)
                            sp.left = p;
                        else
                            sp.right = p;
                    }
                    if ((s.right = pr) != null)
                        pr.parent = s;
                }
                p.left = null;
                if ((p.right = sr) != null)
                    sr.parent = p;
                if ((s.left = pl) != null)
                    pl.parent = s;
                if ((s.parent = pp) == null)
                    root = s;
                else if (p == pp.left)
                    pp.left = s;
                else
                    pp.right = s;
                if (sr != null)
                    replacement = sr;
                else
                    replacement = p;
            } else if (pl != null)
                replacement = pl;
            else if (pr != null)
                replacement = pr;
            else
                replacement = p;
            if (replacement != p) {
                TreeNode<K, V> pp = replacement.parent = p.parent;
                if (pp == null)
                    root = replacement;
                else if (p == pp.left)
                    pp.left = replacement;
                else
                    pp.right = replacement;
                p.left = p.right = p.parent = null;
            }

            TreeNode<K, V> r = p.red ? root : balanceDeletion(root, replacement);

            if (replacement == p) {  // detach
                TreeNode<K, V> pp = p.parent;
                p.parent = null;
                if (pp != null) {
                    if (p == pp.left)
                        pp.left = null;
                    else if (p == pp.right)
                        pp.right = null;
                }
            }
            if (movable)
                moveRootToFront(tab, r);
        }

        /**
         * Splits nodes in a tree bin into lower and upper tree bins,
         * or untreeifies if now too small. Called only from resize;
         * see above discussion about split bits and indices.
         * <p>
         * 将树形结构中的节点拆分为较低和较高的树形结构，或者如果现在太小，则将结果拆分。
         * 只能从调整大小调用; 请参阅上面关于分割位和索引的讨论。
         *
         * @param map   the map
         * @param tab   the table for recording bin heads
         * @param index the index of the table being split
         * @param bit   the bit of hash to split on
         */
        final void split(HashMap<K, V> map, Node<K, V>[] tab, int index, int bit) {
            TreeNode<K, V> b = this;
            // Relink into lo and hi lists, preserving order
            TreeNode<K, V> loHead = null, loTail = null;
            TreeNode<K, V> hiHead = null, hiTail = null;
            int lc = 0, hc = 0;
            for (TreeNode<K, V> e = b, next; e != null; e = next) {
                next = (TreeNode<K, V>) e.next;
                e.next = null;
                if ((e.hash & bit) == 0) {
                    if ((e.prev = loTail) == null)
                        loHead = e;
                    else
                        loTail.next = e;
                    loTail = e;
                    ++lc;
                } else {
                    if ((e.prev = hiTail) == null)
                        hiHead = e;
                    else
                        hiTail.next = e;
                    hiTail = e;
                    ++hc;
                }
            }

            if (loHead != null) {
                if (lc <= UNTREEIFY_THRESHOLD)
                    tab[index] = loHead.untreeify(map);
                else {
                    tab[index] = loHead;
                    if (hiHead != null) // (else is already treeified)
                        loHead.treeify(tab);
                }
            }
            if (hiHead != null) {
                if (hc <= UNTREEIFY_THRESHOLD)
                    tab[index + bit] = hiHead.untreeify(map);
                else {
                    tab[index + bit] = hiHead;
                    if (loHead != null)
                        hiHead.treeify(tab);
                }
            }
        }

        /* ------------------------------------------------------------ */
        // Red-black tree methods, all adapted from CLR
        // 红黑树方法，全部由 CLR 改编
        /*
        Q:CLR 是什么
        https://en.wikipedia.org/wiki/CLR
        A nickname for Introduction to Algorithms by Cormen, Leiserson, and Rivest
         */

        /*
        左旋
                    pp                      pp
                   /                       /
                  p                       r
                   \                     /
                    r                   p
                   /                     \
                  rl                      rl
         */
        static <K, V> TreeNode<K, V> rotateLeft(TreeNode<K, V> root,
                                                TreeNode<K, V> p) {
            TreeNode<K, V> r, pp, rl;
            if (p != null && (r = p.right) != null) {
                /*
                ①将 r.left 赋给 p.right
                        pp
                       /
                       p        r
                        \
                        rl
                 */
                if ((rl = p.right = r.left) != null)
                    rl.parent = p;

                // ②将 p.parent 赋给 r.parent
                if ((pp = r.parent = p.parent) == null)
                    /* 如果 p 是 root 则把 r 置为 root
                        r=root

                      p
                       \
                       rl
                     */
                    (root = r).red = false;
                else if (pp.left == p)
                    /*
                    如果 p 是 pp 左节点，则 r 置为 pp 左节点
                            pp
                           /
                          r

                        p
                         \
                          rl
                     */
                    pp.left = r;
                else
                    /*
                    如果 p 是 pp 右节点，则 r 置为 pp 右节点

                       pp
                        \
                          r

                        p
                         \
                          rl
                     */
                    pp.right = r;
                /*
                ③连接 p 和 r
                            pp
                           /
                          r
                         /
                        p
                         \
                          rl
                 */
                r.left = p;
                p.parent = r;
            }
            return root;
        }

        /*
        右旋
                pp                  pp
               /                   /
              p                    l
             /                      \
            l                        p
             \                      /
              lr                    lr
         */
        static <K, V> TreeNode<K, V> rotateRight(TreeNode<K, V> root,
                                                 TreeNode<K, V> p) {
            TreeNode<K, V> l, pp, lr;
            if (p != null && (l = p.left) != null) {
                /*
                ①将 l.right 赋给 p.left，如果 l.right 不为 null ，将其 parent 设为 p
                        pp
                       /
                      p
                  l  /
                    lr
                 */
                if ((lr = p.left = l.right) != null)
                    lr.parent = p;
                // 将 p.parent 赋给 l.parent
                if ((pp = l.parent = p.parent) == null)
                    /*
                    如果 p 为 root，则 l 置为 root
                        l

                           p
                          /
                         lr
                     */
                    (root = l).red = false;
                else if (pp.right == p)
                    /*
                    如果 p 为 pp.right，则将 l 置为 pp.right
                            pp
                              \
                              l

                                 p
                                /
                               lr
                     */
                    pp.right = l;
                else
                    /*
                    如果 p 为 pp.left，则将 l 置为 pp.left
                                pp
                               /
                              l

                                p
                               /
                              lr
                     */
                    pp.left = l;
                /*
                ③连接 p 和 l
                            pp
                           /
                          l
                           \
                           p
                          /
                          lr
                 */
                l.right = p;
                p.parent = l;
            }
            return root;
        }

        /**
         * 平衡插入
         */
        static <K, V> TreeNode<K, V> balanceInsertion(TreeNode<K, V> root,
                                                      TreeNode<K, V> x) {
            //插入节点为红
            x.red = true;
            for (TreeNode<K, V> xp, xpp, xppl, xppr; ; ) {
                if ((xp = x.parent) == null) {
                    //根节点，涂黑返回
                    x.red = false;
                    return x;
                } else if (!xp.red || (xpp = xp.parent) == null)
                    //父节点是黑的返回，或者？什么情况 xpp 为 null？还是只用来赋值？
                    return root;
                //父节点为红，此时祖父结点黑，考虑叔结点的情况
                if (xp == (xppl = xpp.left)) {
                    //父节点为祖父节点的左节点
                    if ((xppr = xpp.right) != null && xppr.red) {
                        /*
                        叔节点为红
                        此时的情况为当前为红，父为红，叔为红，祖父为黑
                        将父节点、叔节点涂黑
                        祖父节点涂红
                        置当前为祖父节点，回溯
                         */
                        xppr.red = false;
                        xp.red = false;
                        xpp.red = true;
                        x = xpp;
                    } else {
                        //父节点为红，叔节点为黑，考虑两种左情况
                        if (x == xp.right) {
                            /*
                            当前为右结点
                            左右
                            先进行左旋，以 父节点为当前节点
                             */
                            root = rotateLeft(root, x = xp);
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        /*
                        else 或 左旋后都满足的情况
                        左左
                        父节点为红，叔节点为黑
                        当前为红，是左节点
                         */

                        if (xp != null) {
                            /*
                            将父节点涂黑,将祖父节点涂红
                            以祖父节点为支点作右旋
                            注意没有置当前节点，当前节点父节点已为黑，结束
                             */
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateRight(root, xpp);
                            }
                        }
                    }
                } else {
                    //对称，父节点为右节点
                    if (xppl != null && xppl.red) {
                        /*
                        父节点为红，叔节点为红
                        将父节点、叔节点涂黑
                        祖父节点涂红
                        置当前为祖父节点
                         */
                        xppl.red = false;
                        xp.red = false;
                        xpp.red = true;
                        x = xpp;
                    } else {
                        //父节点为红，叔节点为黑
                        if (x == xp.left) {
                            //当前为左节点，置当前为父节点，进行右旋
                            root = rotateRight(root, x = xp);
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        /*
                        else 或 右旋后，当前为右节点，父为红，叔为黑
                        将父涂黑，将祖父涂红，祖父为支点进行左旋
                         */
                        if (xp != null) {
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateLeft(root, xpp);
                            }
                        }
                    }
                }
            }
        }

        static <K, V> TreeNode<K, V> balanceDeletion(TreeNode<K, V> root,
                                                     TreeNode<K, V> x) {
            for (TreeNode<K, V> xp, xpl, xpr; ; ) {
                if (x == null || x == root)
                    //根节点
                    return root;
                else if ((xp = x.parent) == null) {
                    //涂黑
                    x.red = false;
                    return x;
                } else if (x.red) {
                    //如果是红，说明移走的是黑，涂黑即可
                    x.red = false;
                    return root;
                } else if ((xpl = xp.left) == x) {
                    //为黑，为左节点
                    if ((xpr = xp.right) != null && xpr.red) {
                        /*
                        兄弟节点为红
                        说明父节点为黑，兄第节点的子节点为黑
                        将父节点涂红，兄弟节点涂黑
                        以父节点为支点左旋后，兄弟节点的左节点变为当前节点的兄弟节点（为黑）
                         */
                        xpr.red = false;
                        xp.red = true;
                        root = rotateLeft(root, xp);
                        xpr = (xp = x.parent) == null ? null : xp.right;
                    }
                    //兄弟节点为黑
                    if (xpr == null)
                        x = xp;
                    else {
                        TreeNode<K, V> sl = xpr.left, sr = xpr.right;
                        if ((sr == null || !sr.red) &&
                                (sl == null || !sl.red)) {
                            /*
                            兄弟节点的子节点均为黑，将兄弟节点涂红
                            当前节点置为父节点
                             */
                            xpr.red = true;
                            x = xp;
                        } else {
                            if (sr == null || !sr.red) {
                                /*
                                兄弟节点的右节点为黑，兄弟节点左节点为红
                                将兄弟节点的左节点涂黑，兄弟节点涂红
                                以兄弟节点为支点右旋
                                右旋后，原兄弟结点的左结点变为当前节点的兄弟节点（为黑，且右节点为红）
                                 */
                                if (sl != null)
                                    sl.red = false;
                                xpr.red = true;
                                root = rotateRight(root, xpr);
                                xpr = (xp = x.parent) == null ?
                                        null : xp.right;
                            }
                            /*
                            兄弟结点的右节点为红
                            将兄弟节点涂为父节点的颜色
                            兄弟节点的右节点涂黑
                            父节点涂黑
                            然后以父节点为支点左旋
                            左旋后原兄弟节点成为，原父节点（黑）和原兄弟结点的右节点（为黑）的父节点
                             */
                            if (xpr != null) {
                                xpr.red = (xp == null) ? false : xp.red;
                                if ((sr = xpr.right) != null)
                                    sr.red = false;
                            }
                            if (xp != null) {
                                xp.red = false;
                                root = rotateLeft(root, xp);
                            }
                            x = root;
                        }
                    }
                } else { // symmetric
                    //当前节点为黑，为右节点
                    if (xpl != null && xpl.red) {
                        /*
                        左兄弟节点为红，说明父节点及左节点的子节点均为黑
                        将左节点涂黑，父节点涂红，以父节点为支点右旋
                        右旋后原兄弟节点的右节点（黑）成为新的左兄弟节点
                         */
                        xpl.red = false;
                        xp.red = true;
                        root = rotateRight(root, xp);
                        xpl = (xp = x.parent) == null ? null : xp.left;
                    }
                    if (xpl == null)
                        x = xp;
                    else {
                        //左兄弟点为黑
                        TreeNode<K, V> sl = xpl.left, sr = xpl.right;
                        if ((sl == null || !sl.red) &&
                                (sr == null || !sr.red)) {
                            //左兄弟结点的子节点均为黑，将左兄弟结点涂黑
                            //当前节点置为父节点（如果为红会在下一次被置黑）
                            xpl.red = true;
                            x = xp;
                        } else {
                            if (sl == null || !sl.red) {
                                /*
                                左兄弟节点为黑，左兄弟节点的左节点为黑，
                                将左兄弟涂红，兄弟的右节点涂黑，左兄弟左旋
                                左旋后原左兄弟的右节点（为黑）成为左兄弟
                                原左兄弟成为左兄弟的左节点（为红）
                                 */
                                if (sr != null)
                                    sr.red = false;
                                xpl.red = true;
                                root = rotateLeft(root, xpl);
                                xpl = (xp = x.parent) == null ?
                                        null : xp.left;
                            }
                            /*
                            左兄弟为黑，左兄弟的左节点为红
                            左兄弟涂为父节点颜色
                            左兄弟的左节点涂黑，父节点涂黑，父节点右旋
                            右旋后原左兄弟成为，原左兄弟的左节点（黑）和原父节点（黑）的父节点
                             */
                            if (xpl != null) {
                                xpl.red = (xp == null) ? false : xp.red;
                                if ((sl = xpl.left) != null)
                                    sl.red = false;
                            }
                            if (xp != null) {
                                xp.red = false;
                                root = rotateRight(root, xp);
                            }
                            x = root;
                        }
                    }
                }
            }
        }

        /**
         * Recursive invariant check
         * 递归不变检查
         */
        static <K, V> boolean checkInvariants(TreeNode<K, V> t) {
            TreeNode<K, V> tp = t.parent, tl = t.left, tr = t.right,
                    tb = t.prev, tn = (TreeNode<K, V>) t.next;
            if (tb != null && tb.next != t)
                return false;
            if (tn != null && tn.prev != t)
                return false;
            if (tp != null && t != tp.left && t != tp.right)
                return false;
            if (tl != null && (tl.parent != t || tl.hash > t.hash))
                return false;
            if (tr != null && (tr.parent != t || tr.hash < t.hash))
                return false;
            if (t.red && tl != null && tl.red && tr != null && tr.red)
                return false;
            if (tl != null && !checkInvariants(tl))
                return false;
            if (tr != null && !checkInvariants(tr))
                return false;
            return true;
        }
    }

}
