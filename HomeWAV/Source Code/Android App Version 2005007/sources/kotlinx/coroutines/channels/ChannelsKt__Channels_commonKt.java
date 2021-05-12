package kotlinx.coroutines.channels;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ð\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010 \n\u0000\n\u0002\u0010!\n\u0002\b\u0011\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010#\n\u0000\n\u0002\u0010\"\n\u0002\b\u0006\u001aJ\u0010\u0002\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003j\u0002`\t2\u001a\u0010\n\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\f0\u000b\"\u0006\u0012\u0002\b\u00030\fH\u0007¢\u0006\u0002\u0010\r\u001a5\u0010\u000e\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a!\u0010\u0013\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a5\u0010\u0013\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aY\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u0016\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0004\b\u0002\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00100\f2\u001e\u0010\u0019\u001a\u001a\u0012\u0004\u0012\u0002H\u0010\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u001a0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aG\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aa\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u0016\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0004\b\u0002\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u00032\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00180\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a]\u0010\u001f\u001a\u0002H \"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0018\b\u0002\u0010 *\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0017\u0012\u0006\b\u0000\u0012\u0002H\u00100!*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002H 2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u0003HHø\u0001\u0000¢\u0006\u0002\u0010#\u001aw\u0010\u001f\u001a\u0002H \"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0004\b\u0002\u0010\u0018\"\u0018\b\u0003\u0010 *\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0017\u0012\u0006\b\u0000\u0012\u0002H\u00180!*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002H 2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u00032\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00180\u0003HHø\u0001\u0000¢\u0006\u0002\u0010$\u001ao\u0010%\u001a\u0002H \"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0004\b\u0002\u0010\u0018\"\u0018\b\u0003\u0010 *\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0017\u0012\u0006\b\u0000\u0012\u0002H\u00180!*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002H 2\u001e\u0010\u0019\u001a\u001a\u0012\u0004\u0012\u0002H\u0010\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u001a0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010#\u001aC\u0010&\u001a\u0002H'\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H\u00100(2\u001d\u0010)\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\f\u0012\u0004\u0012\u0002H'0\u0003¢\u0006\u0002\b*H\b¢\u0006\u0002\u0010+\u001aC\u0010&\u001a\u0002H'\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H\u00100\f2\u001d\u0010)\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\f\u0012\u0004\u0012\u0002H'0\u0003¢\u0006\u0002\b*H\b¢\u0006\u0002\u0010,\u001a5\u0010-\u001a\u00020\b\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100(2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\b0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010/\u001a5\u0010-\u001a\u00020\b\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\b0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a;\u00100\u001a\u00020\b\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0018\u0010.\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001001\u0012\u0004\u0012\u00020\b0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a1\u00102\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003j\u0002`\t*\u0006\u0012\u0002\b\u00030\fH\u0007\u001a!\u00103\u001a\u000204\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a5\u00103\u001a\u000204\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u001e\u00105\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH\u0007\u001aZ\u00106\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u0002082\"\u00109\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00170;\u0012\u0006\u0012\u0004\u0018\u00010<0:H\u0007ø\u0001\u0000¢\u0006\u0002\u0010=\u001a0\u0010>\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010?\u001a\u0002042\b\b\u0002\u00107\u001a\u000208H\u0007\u001aT\u0010@\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u0002082\"\u0010\u0011\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0;\u0012\u0006\u0012\u0004\u0018\u00010<0:H\u0007ø\u0001\u0000¢\u0006\u0002\u0010=\u001a)\u0010A\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010B\u001a\u000204H@ø\u0001\u0000¢\u0006\u0002\u0010C\u001a=\u0010D\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010B\u001a\u0002042\u0012\u0010E\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u0002H\u00100\u0003HHø\u0001\u0000¢\u0006\u0002\u0010F\u001a+\u0010G\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010B\u001a\u000204H@ø\u0001\u0000¢\u0006\u0002\u0010C\u001aT\u0010H\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u0002082\"\u0010\u0011\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0;\u0012\u0006\u0012\u0004\u0018\u00010<0:H\u0007ø\u0001\u0000¢\u0006\u0002\u0010=\u001ai\u0010I\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u00020827\u0010\u0011\u001a3\b\u0001\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0;\u0012\u0006\u0012\u0004\u0018\u00010<0JH\u0007ø\u0001\u0000¢\u0006\u0002\u0010K\u001ad\u0010L\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u0010\b\u0001\u0010M*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100N*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2'\u0010\u0011\u001a#\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0:HHø\u0001\u0000¢\u0006\u0002\u0010O\u001ab\u0010L\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u000e\b\u0001\u0010M*\b\u0012\u0004\u0012\u0002H\u00100P*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2'\u0010\u0011\u001a#\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0:HHø\u0001\u0000¢\u0006\u0002\u0010Q\u001aT\u0010R\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u0002082\"\u0010\u0011\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0;\u0012\u0006\u0012\u0004\u0018\u00010<0:H\u0007ø\u0001\u0000¢\u0006\u0002\u0010=\u001a$\u0010S\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\b\b\u0000\u0010\u0010*\u00020<*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\fH\u0007\u001aA\u0010T\u001a\u0002HM\"\b\b\u0000\u0010\u0010*\u00020<\"\u0010\b\u0001\u0010M*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100N*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\f2\u0006\u0010\"\u001a\u0002HMH@ø\u0001\u0000¢\u0006\u0002\u0010U\u001a?\u0010T\u001a\u0002HM\"\b\b\u0000\u0010\u0010*\u00020<\"\u000e\b\u0001\u0010M*\b\u0012\u0004\u0012\u0002H\u00100P*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\f2\u0006\u0010\"\u001a\u0002HMH@ø\u0001\u0000¢\u0006\u0002\u0010V\u001aO\u0010W\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u0010\b\u0001\u0010M*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100N*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010X\u001aM\u0010W\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u000e\b\u0001\u0010M*\b\u0012\u0004\u0012\u0002H\u00100P*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010Y\u001aO\u0010Z\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u0010\b\u0001\u0010M*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100N*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010X\u001aM\u0010Z\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u000e\b\u0001\u0010M*\b\u0012\u0004\u0012\u0002H\u00100P*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010Y\u001a7\u0010[\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a7\u0010\\\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a!\u0010]\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a5\u0010]\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a#\u0010^\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a7\u0010^\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a`\u0010_\u001a\b\u0012\u0004\u0012\u0002H'0\f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u0002082(\u0010\u0019\u001a$\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0\f0;\u0012\u0006\u0012\u0004\u0018\u00010<0:H\u0007ø\u0001\u0000¢\u0006\u0002\u0010=\u001aX\u0010`\u001a\u0002H'\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010a\u001a\u0002H'2'\u0010b\u001a#\u0012\u0013\u0012\u0011H'¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(c\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H'0:HHø\u0001\u0000¢\u0006\u0002\u0010d\u001am\u0010e\u001a\u0002H'\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010a\u001a\u0002H'2<\u0010b\u001a8\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0013\u0012\u0011H'¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(c\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H'0JHHø\u0001\u0000¢\u0006\u0002\u0010f\u001aM\u0010g\u001a\u0014\u0012\u0004\u0012\u0002H\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100h0\u0016\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001ag\u0010g\u001a\u0014\u0012\u0004\u0012\u0002H\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180h0\u0016\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0004\b\u0002\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u00032\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00180\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001aa\u0010i\u001a\u0002H \"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u001c\b\u0002\u0010 *\u0016\u0012\u0006\b\u0000\u0012\u0002H\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100j0!*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002H 2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u0003HHø\u0001\u0000¢\u0006\u0002\u0010#\u001a{\u0010i\u001a\u0002H \"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0017\"\u0004\b\u0002\u0010\u0018\"\u001c\b\u0003\u0010 *\u0016\u0012\u0006\b\u0000\u0012\u0002H\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180j0!*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002H 2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00170\u00032\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00180\u0003HHø\u0001\u0000¢\u0006\u0002\u0010$\u001a)\u0010k\u001a\u000204\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010l\u001a\u0002H\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010m\u001a5\u0010n\u001a\u000204\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a5\u0010o\u001a\u000204\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a!\u0010p\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a5\u0010p\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a)\u0010q\u001a\u000204\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010l\u001a\u0002H\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010m\u001a#\u0010r\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a7\u0010r\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aX\u0010s\u001a\b\u0012\u0004\u0012\u0002H'0\f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u0002082\"\u0010\u0019\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0;\u0012\u0006\u0012\u0004\u0018\u00010<0:ø\u0001\u0000¢\u0006\u0002\u0010=\u001ao\u0010t\u001a\b\u0012\u0004\u0012\u0002H'0\f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u00020827\u0010\u0019\u001a3\b\u0001\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0;\u0012\u0006\u0012\u0004\u0018\u00010<0JH\u0007ø\u0001\u0000¢\u0006\u0002\u0010K\u001au\u0010u\u001a\b\u0012\u0004\u0012\u0002H'0\f\"\u0004\b\u0000\u0010\u0010\"\b\b\u0001\u0010'*\u00020<*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u00020829\u0010\u0019\u001a5\b\u0001\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0004\u0012\u0002H\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H'0;\u0012\u0006\u0012\u0004\u0018\u00010<0JH\u0007ø\u0001\u0000¢\u0006\u0002\u0010K\u001ap\u0010v\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\b\b\u0001\u0010'*\u00020<\"\u0010\b\u0002\u0010M*\n\u0012\u0006\b\u0000\u0012\u0002H'0N*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2)\u0010\u0019\u001a%\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0004\u0012\u0002H\u0010\u0012\u0006\u0012\u0004\u0018\u0001H'0:HHø\u0001\u0000¢\u0006\u0002\u0010O\u001an\u0010v\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\b\b\u0001\u0010'*\u00020<\"\u000e\b\u0002\u0010M*\b\u0012\u0004\u0012\u0002H'0P*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2)\u0010\u0019\u001a%\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0004\u0012\u0002H\u0010\u0012\u0006\u0012\u0004\u0018\u0001H'0:HHø\u0001\u0000¢\u0006\u0002\u0010Q\u001aj\u0010w\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'\"\u0010\b\u0002\u0010M*\n\u0012\u0006\b\u0000\u0012\u0002H'0N*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2'\u0010\u0019\u001a#\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H'0:HHø\u0001\u0000¢\u0006\u0002\u0010O\u001ah\u0010w\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'\"\u000e\b\u0002\u0010M*\b\u0012\u0004\u0012\u0002H'0P*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2'\u0010\u0019\u001a#\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H'0:HHø\u0001\u0000¢\u0006\u0002\u0010Q\u001a`\u0010x\u001a\b\u0012\u0004\u0012\u0002H'0\f\"\u0004\b\u0000\u0010\u0010\"\b\b\u0001\u0010'*\u00020<*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u0002082$\u0010\u0019\u001a \b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H'0;\u0012\u0006\u0012\u0004\u0018\u00010<0:H\u0007ø\u0001\u0000¢\u0006\u0002\u0010=\u001a[\u0010y\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\b\b\u0001\u0010'*\u00020<\"\u0010\b\u0002\u0010M*\n\u0012\u0006\b\u0000\u0012\u0002H'0N*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u0002H\u0010\u0012\u0006\u0012\u0004\u0018\u0001H'0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010X\u001aY\u0010y\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\b\b\u0001\u0010'*\u00020<\"\u000e\b\u0002\u0010M*\b\u0012\u0004\u0012\u0002H'0P*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u0002H\u0010\u0012\u0006\u0012\u0004\u0018\u0001H'0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010Y\u001aU\u0010z\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'\"\u0010\b\u0002\u0010M*\n\u0012\u0006\b\u0000\u0012\u0002H'0N*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H'0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010X\u001aS\u0010z\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'\"\u000e\b\u0002\u0010M*\b\u0012\u0004\u0012\u0002H'0P*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HM2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H'0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010Y\u001aG\u0010{\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010\"\u000e\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H'0|*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H'0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aA\u0010}\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u001b\u0010~\u001a\u0017\u0012\u0006\b\u0000\u0012\u0002H\u00100j\u000b\u0012\u0006\b\u0000\u0012\u0002H\u0010`\u0001H@ø\u0001\u0000¢\u0006\u0003\u0010\u0001\u001aH\u0010\u0001\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010\"\u000e\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H'0|*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H'0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aB\u0010\u0001\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u001b\u0010~\u001a\u0017\u0012\u0006\b\u0000\u0012\u0002H\u00100j\u000b\u0012\u0006\b\u0000\u0012\u0002H\u0010`\u0001H@ø\u0001\u0000¢\u0006\u0003\u0010\u0001\u001a\"\u0010\u0001\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a6\u0010\u0001\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aN\u0010\u0001\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100h\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100h0\u001a\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a[\u0010\u0001\u001a\u0003H\u0001\"\u0005\b\u0000\u0010\u0001\"\t\b\u0001\u0010\u0010*\u0003H\u0001*\b\u0012\u0004\u0012\u0002H\u00100\f2)\u0010b\u001a%\u0012\u0014\u0012\u0012H\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(c\u0012\u0004\u0012\u0002H\u0010\u0012\u0005\u0012\u0003H\u00010:HHø\u0001\u0000¢\u0006\u0003\u0010\u0001\u001ap\u0010\u0001\u001a\u0003H\u0001\"\u0005\b\u0000\u0010\u0001\"\t\b\u0001\u0010\u0010*\u0003H\u0001*\b\u0012\u0004\u0012\u0002H\u00100\f2>\u0010b\u001a:\u0012\u0013\u0012\u001104¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(B\u0012\u0014\u0012\u0012H\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(c\u0012\u0004\u0012\u0002H\u0010\u0012\u0005\u0012\u0003H\u00010JHHø\u0001\u0000¢\u0006\u0003\u0010\u0001\u001a%\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\b\b\u0000\u0010\u0010*\u00020<*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\fH\u0007\u001a\"\u0010\u0001\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a6\u0010\u0001\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a$\u0010\u0001\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a8\u0010\u0001\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000f0\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a6\u0010\u0001\u001a\u000204\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002040\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a8\u0010\u0001\u001a\u00030\u0001\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0013\u00109\u001a\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\u0005\u0012\u00030\u00010\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a1\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010?\u001a\u0002042\b\b\u0002\u00107\u001a\u000208H\u0007\u001aU\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u00100\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u0002082\"\u0010\u0011\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0;\u0012\u0006\u0012\u0004\u0018\u00010<0:H\u0007ø\u0001\u0000¢\u0006\u0002\u0010=\u001a:\u0010\u0001\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u000e\b\u0001\u0010M*\b\u0012\u0004\u0012\u0002H\u00100P*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HMH@ø\u0001\u0000¢\u0006\u0002\u0010V\u001a<\u0010\u0001\u001a\u0002HM\"\u0004\b\u0000\u0010\u0010\"\u0010\b\u0001\u0010M*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100N*\b\u0012\u0004\u0012\u0002H\u00100\f2\u0006\u0010\"\u001a\u0002HMH@ø\u0001\u0000¢\u0006\u0002\u0010U\u001a(\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u00100h\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a@\u0010\u0001\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u0016\"\u0004\b\u0000\u0010\u0017\"\u0004\b\u0001\u0010\u0018*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u001a0\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001aW\u0010\u0001\u001a\u0002H \"\u0004\b\u0000\u0010\u0017\"\u0004\b\u0001\u0010\u0018\"\u0018\b\u0002\u0010 *\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0017\u0012\u0006\b\u0000\u0012\u0002H\u00180!*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u001a0\f2\u0006\u0010\"\u001a\u0002H H@ø\u0001\u0000¢\u0006\u0003\u0010\u0001\u001a(\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u00100j\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a)\u0010\u0001\u001a\t\u0012\u0004\u0012\u0002H\u00100\u0001\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a)\u0010\u0001\u001a\t\u0012\u0004\u0012\u0002H\u00100\u0001\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a/\u0010\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u0010010\f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\f2\b\b\u0002\u00107\u001a\u000208H\u0007\u001aA\u0010\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H'0\u001a0\f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'*\b\u0012\u0004\u0012\u0002H\u00100\f2\r\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H'0\fH\u0004\u001a~\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u00180\f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010'\"\u0004\b\u0002\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00100\f2\r\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H'0\f2\b\b\u0002\u00107\u001a\u00020828\u0010\u0019\u001a4\u0012\u0014\u0012\u0012H\u0010¢\u0006\r\b\u0005\u0012\t\b\u0006\u0012\u0005\b\b( \u0001\u0012\u0014\u0012\u0012H'¢\u0006\r\b\u0005\u0012\t\b\u0006\u0012\u0005\b\b(¡\u0001\u0012\u0004\u0012\u0002H\u00180:H\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006¢\u0001"}, d2 = {"DEFAULT_CLOSE_MESSAGE", "", "consumesAll", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "channels", "", "Lkotlinx/coroutines/channels/ReceiveChannel;", "([Lkotlinx/coroutines/channels/ReceiveChannel;)Lkotlin/jvm/functions/Function1;", "all", "", "E", "predicate", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "any", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "associate", "", "K", "V", "transform", "Lkotlin/Pair;", "associateBy", "keySelector", "valueTransform", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "associateByTo", "M", "", "destination", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "associateTo", "consume", "R", "Lkotlinx/coroutines/channels/BroadcastChannel;", "block", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/channels/BroadcastChannel;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "consumeEach", "action", "(Lkotlinx/coroutines/channels/BroadcastChannel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "consumeEachIndexed", "Lkotlin/collections/IndexedValue;", "consumes", "count", "", "distinct", "distinctBy", "context", "Lkotlin/coroutines/CoroutineContext;", "selector", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "drop", "n", "dropWhile", "elementAt", "index", "(Lkotlinx/coroutines/channels/ReceiveChannel;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "elementAtOrElse", "defaultValue", "(Lkotlinx/coroutines/channels/ReceiveChannel;ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "elementAtOrNull", "filter", "filterIndexed", "Lkotlin/Function3;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/channels/ReceiveChannel;", "filterIndexedTo", "C", "", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Collection;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/SendChannel;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlinx/coroutines/channels/SendChannel;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "filterNot", "filterNotNull", "filterNotNullTo", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "filterNotTo", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlinx/coroutines/channels/SendChannel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "filterTo", "find", "findLast", "first", "firstOrNull", "flatMap", "fold", "initial", "operation", "acc", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foldIndexed", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "groupBy", "", "groupByTo", "", "indexOf", "element", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "indexOfFirst", "indexOfLast", "last", "lastIndexOf", "lastOrNull", "map", "mapIndexed", "mapIndexedNotNull", "mapIndexedNotNullTo", "mapIndexedTo", "mapNotNull", "mapNotNullTo", "mapTo", "maxBy", "", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Comparator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "minBy", "minWith", "none", "partition", "reduce", "S", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reduceIndexed", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requireNoNulls", "single", "singleOrNull", "sumBy", "sumByDouble", "", "take", "takeWhile", "toChannel", "toCollection", "toList", "toMap", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toMutableList", "toMutableSet", "", "toSet", "", "withIndex", "zip", "other", "a", "b", "kotlinx-coroutines-core"}, k = 5, mv = {1, 1, 13}, xs = "kotlinx/coroutines/channels/ChannelsKt")
/* compiled from: Channels.common.kt */
final /* synthetic */ class ChannelsKt__Channels_commonKt {
    public static final <E, R> R consume(BroadcastChannel<E> broadcastChannel, Function1<? super ReceiveChannel<? extends E>, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(broadcastChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        ReceiveChannel<E> openSubscription = broadcastChannel.openSubscription();
        try {
            return function1.invoke(openSubscription);
        } finally {
            InlineMarker.finallyStart(1);
            openSubscription.cancel();
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00bb A[Catch:{ all -> 0x007e }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object consumeEach(kotlinx.coroutines.channels.BroadcastChannel<E> r10, kotlin.jvm.functions.Function1<? super E, kotlin.Unit> r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0081
            if (r2 == r4) goto L_0x005c
            if (r2 != r3) goto L_0x0054
            java.lang.Object r10 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r11 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.BroadcastChannel r5 = (kotlinx.coroutines.channels.BroadcastChannel) r5
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.BroadcastChannel r7 = (kotlinx.coroutines.channels.BroadcastChannel) r7
            boolean r8 = r12 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x007e }
            if (r8 != 0) goto L_0x004f
        L_0x0045:
            r9 = r0
            r0 = r10
            r10 = r7
            r7 = r11
            r11 = r5
            r5 = r2
            r2 = r1
            r1 = r9
            goto L_0x00d0
        L_0x004f:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12     // Catch:{ all -> 0x007e }
            java.lang.Throwable r10 = r12.exception     // Catch:{ all -> 0x007e }
            throw r10     // Catch:{ all -> 0x007e }
        L_0x0054:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x005c:
            java.lang.Object r10 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r11 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.BroadcastChannel r5 = (kotlinx.coroutines.channels.BroadcastChannel) r5
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.BroadcastChannel r7 = (kotlinx.coroutines.channels.BroadcastChannel) r7
            boolean r8 = r12 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x007e }
            if (r8 != 0) goto L_0x0079
            goto L_0x00b3
        L_0x0079:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12     // Catch:{ all -> 0x007e }
            java.lang.Throwable r10 = r12.exception     // Catch:{ all -> 0x007e }
            throw r10     // Catch:{ all -> 0x007e }
        L_0x007e:
            r10 = move-exception
            goto L_0x00e4
        L_0x0081:
            boolean r2 = r12 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x00ee
            kotlinx.coroutines.channels.ReceiveChannel r2 = r10.openSubscription()
            kotlinx.coroutines.channels.ChannelIterator r12 = r2.iterator()     // Catch:{ all -> 0x007e }
            r5 = r2
            r6 = r5
            r2 = r1
            r1 = r0
            r0 = r12
            r12 = r11
            r11 = r10
        L_0x0094:
            r1.L$0 = r10     // Catch:{ all -> 0x00e2 }
            r1.L$1 = r12     // Catch:{ all -> 0x00e2 }
            r1.L$2 = r11     // Catch:{ all -> 0x00e2 }
            r1.L$3 = r5     // Catch:{ all -> 0x00e2 }
            r1.L$4 = r6     // Catch:{ all -> 0x00e2 }
            r1.L$5 = r0     // Catch:{ all -> 0x00e2 }
            r1.label = r4     // Catch:{ all -> 0x00e2 }
            java.lang.Object r7 = r0.hasNext(r1)     // Catch:{ all -> 0x00e2 }
            if (r7 != r2) goto L_0x00a9
            return r2
        L_0x00a9:
            r9 = r7
            r7 = r10
            r10 = r0
            r0 = r1
            r1 = r2
            r2 = r5
            r5 = r11
            r11 = r6
            r6 = r12
            r12 = r9
        L_0x00b3:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ all -> 0x007e }
            boolean r12 = r12.booleanValue()     // Catch:{ all -> 0x007e }
            if (r12 == 0) goto L_0x00d6
            r0.L$0 = r7     // Catch:{ all -> 0x007e }
            r0.L$1 = r6     // Catch:{ all -> 0x007e }
            r0.L$2 = r5     // Catch:{ all -> 0x007e }
            r0.L$3 = r2     // Catch:{ all -> 0x007e }
            r0.L$4 = r11     // Catch:{ all -> 0x007e }
            r0.L$5 = r10     // Catch:{ all -> 0x007e }
            r0.label = r3     // Catch:{ all -> 0x007e }
            java.lang.Object r12 = r10.next(r0)     // Catch:{ all -> 0x007e }
            if (r12 != r1) goto L_0x0045
            return r1
        L_0x00d0:
            r6.invoke(r12)     // Catch:{ all -> 0x00e2 }
            r12 = r6
            r6 = r7
            goto L_0x0094
        L_0x00d6:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x007e }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            r2.cancel()
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            return r10
        L_0x00e2:
            r10 = move-exception
            r2 = r5
        L_0x00e4:
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            r2.cancel()
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r10
        L_0x00ee:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
            java.lang.Throwable r10 = r12.exception
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeEach(kotlinx.coroutines.channels.BroadcastChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Function1<Throwable, Unit> consumes(ReceiveChannel<?> receiveChannel) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        return new ChannelsKt__Channels_commonKt$consumes$1(receiveChannel);
    }

    public static final Function1<Throwable, Unit> consumesAll(ReceiveChannel<?>... receiveChannelArr) {
        Intrinsics.checkParameterIsNotNull(receiveChannelArr, "channels");
        return new ChannelsKt__Channels_commonKt$consumesAll$1(receiveChannelArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r2.cancel(r3);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R> R consume(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r2, kotlin.jvm.functions.Function1<? super kotlinx.coroutines.channels.ReceiveChannel<? extends E>, ? extends R> r3) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            java.lang.String r0 = "block"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            java.lang.Object r3 = r3.invoke(r2)     // Catch:{ all -> 0x001c }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r2.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r3
        L_0x001c:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x001e }
        L_0x001e:
            r0 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r2.cancel(r3)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consume(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00af A[Catch:{ all -> 0x00ee }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00be A[Catch:{ all -> 0x00ee }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object consumeEach(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, kotlin.jvm.functions.Function1<? super E, kotlin.Unit> r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            boolean r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$3
            if (r0 == 0) goto L_0x0014
            r0 = r13
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$3
            r0.<init>(r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0088
            if (r2 == r4) goto L_0x005d
            if (r2 != r3) goto L_0x0055
            java.lang.Object r11 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r2 = r0.L$3
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            boolean r8 = r13 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0084 }
            if (r8 != 0) goto L_0x0050
            r9 = r7
            r7 = r11
            r11 = r9
            r10 = r0
            r0 = r12
            r12 = r5
            r5 = r1
            r1 = r2
            r2 = r10
            goto L_0x00db
        L_0x0050:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13     // Catch:{ all -> 0x0084 }
            java.lang.Throwable r11 = r13.exception     // Catch:{ all -> 0x0084 }
            throw r11     // Catch:{ all -> 0x0084 }
        L_0x0055:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x005d:
            java.lang.Object r11 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r2 = r0.L$3
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            boolean r8 = r13 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0084 }
            if (r8 != 0) goto L_0x007f
            r9 = r13
            r13 = r12
            r12 = r5
            r5 = r7
            r7 = r9
            goto L_0x00b6
        L_0x007f:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13     // Catch:{ all -> 0x0084 }
            java.lang.Throwable r11 = r13.exception     // Catch:{ all -> 0x0084 }
            throw r11     // Catch:{ all -> 0x0084 }
        L_0x0084:
            r11 = move-exception
            r12 = r5
            goto L_0x00f4
        L_0x0088:
            boolean r2 = r13 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x0100
            r13 = 0
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            kotlinx.coroutines.channels.ChannelIterator r2 = r11.iterator()     // Catch:{ all -> 0x00f0 }
            r5 = r1
            r6 = r2
            r1 = r13
            r2 = r0
            r13 = r11
            r0 = r12
            r12 = r13
        L_0x009a:
            r2.L$0 = r11     // Catch:{ all -> 0x00ee }
            r2.L$1 = r0     // Catch:{ all -> 0x00ee }
            r2.L$2 = r12     // Catch:{ all -> 0x00ee }
            r2.L$3 = r1     // Catch:{ all -> 0x00ee }
            r2.L$4 = r13     // Catch:{ all -> 0x00ee }
            r2.L$5 = r6     // Catch:{ all -> 0x00ee }
            r2.label = r4     // Catch:{ all -> 0x00ee }
            java.lang.Object r7 = r6.hasNext(r2)     // Catch:{ all -> 0x00ee }
            if (r7 != r5) goto L_0x00af
            return r5
        L_0x00af:
            r9 = r5
            r5 = r11
            r11 = r6
            r6 = r0
            r0 = r2
            r2 = r1
            r1 = r9
        L_0x00b6:
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ all -> 0x00ee }
            boolean r7 = r7.booleanValue()     // Catch:{ all -> 0x00ee }
            if (r7 == 0) goto L_0x00e2
            r0.L$0 = r5     // Catch:{ all -> 0x00ee }
            r0.L$1 = r6     // Catch:{ all -> 0x00ee }
            r0.L$2 = r12     // Catch:{ all -> 0x00ee }
            r0.L$3 = r2     // Catch:{ all -> 0x00ee }
            r0.L$4 = r13     // Catch:{ all -> 0x00ee }
            r0.L$5 = r11     // Catch:{ all -> 0x00ee }
            r0.label = r3     // Catch:{ all -> 0x00ee }
            java.lang.Object r7 = r11.next(r0)     // Catch:{ all -> 0x00ee }
            if (r7 != r1) goto L_0x00d3
            return r1
        L_0x00d3:
            r9 = r7
            r7 = r11
            r11 = r5
            r5 = r1
            r1 = r2
            r2 = r0
            r0 = r13
            r13 = r9
        L_0x00db:
            r6.invoke(r13)     // Catch:{ all -> 0x00ee }
            r13 = r0
            r0 = r6
            r6 = r7
            goto L_0x009a
        L_0x00e2:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00ee }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            r12.cancel(r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            return r11
        L_0x00ee:
            r11 = move-exception
            goto L_0x00f4
        L_0x00f0:
            r12 = move-exception
            r9 = r12
            r12 = r11
            r11 = r9
        L_0x00f4:
            throw r11     // Catch:{ all -> 0x00f5 }
        L_0x00f5:
            r13 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            r12.cancel(r11)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r13
        L_0x0100:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r11 = r13.exception
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeEach(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00dd A[Catch:{ all -> 0x0139 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00de A[Catch:{ all -> 0x0139 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00fb A[Catch:{ all -> 0x0139 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object consumeEachIndexed(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r20, kotlin.jvm.functions.Function1<? super kotlin.collections.IndexedValue<? extends E>, kotlin.Unit> r21, kotlin.coroutines.Continuation<? super kotlin.Unit> r22) {
        /*
            r0 = r22
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEachIndexed$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEachIndexed$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEachIndexed$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEachIndexed$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEachIndexed$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00a6
            if (r3 == r5) goto L_0x0070
            if (r3 != r4) goto L_0x0068
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a1 }
            if (r13 != 0) goto L_0x0063
            r16 = r8
            r8 = r1
            r1 = r9
            r9 = r2
            r2 = r16
        L_0x0056:
            r17 = r11
            r11 = r3
            r3 = r6
            r6 = r17
            r18 = r10
            r10 = r7
            r7 = r18
            goto L_0x011b
        L_0x0063:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a1 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a1 }
            throw r0     // Catch:{ all -> 0x00a1 }
        L_0x0068:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0070:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a1 }
            if (r13 != 0) goto L_0x009c
            r16 = r9
            r9 = r2
            r2 = r8
            r8 = r16
            goto L_0x00f3
        L_0x009c:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a1 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a1 }
            throw r0     // Catch:{ all -> 0x00a1 }
        L_0x00a1:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x013f
        L_0x00a6:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x014c
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r6 = r20.iterator()     // Catch:{ all -> 0x013b }
            r7 = r0
            r8 = r1
            r9 = r2
            r10 = r3
            r11 = r6
            r0 = r20
            r1 = r0
            r2 = r1
            r3 = r2
            r6 = r21
        L_0x00c5:
            r8.L$0 = r0     // Catch:{ all -> 0x0139 }
            r8.L$1 = r6     // Catch:{ all -> 0x0139 }
            r8.L$2 = r7     // Catch:{ all -> 0x0139 }
            r8.L$3 = r1     // Catch:{ all -> 0x0139 }
            r8.L$4 = r2     // Catch:{ all -> 0x0139 }
            r8.L$5 = r10     // Catch:{ all -> 0x0139 }
            r8.L$6 = r3     // Catch:{ all -> 0x0139 }
            r8.L$7 = r11     // Catch:{ all -> 0x0139 }
            r8.label = r5     // Catch:{ all -> 0x0139 }
            java.lang.Object r12 = r11.hasNext(r8)     // Catch:{ all -> 0x0139 }
            if (r12 != r9) goto L_0x00de
            return r9
        L_0x00de:
            r16 = r12
            r12 = r0
            r0 = r16
            r17 = r8
            r8 = r1
            r1 = r17
            r18 = r6
            r6 = r3
            r3 = r11
            r11 = r18
            r19 = r10
            r10 = r7
            r7 = r19
        L_0x00f3:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0139 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0139 }
            if (r0 == 0) goto L_0x012b
            r1.L$0 = r12     // Catch:{ all -> 0x0139 }
            r1.L$1 = r11     // Catch:{ all -> 0x0139 }
            r1.L$2 = r10     // Catch:{ all -> 0x0139 }
            r1.L$3 = r8     // Catch:{ all -> 0x0139 }
            r1.L$4 = r2     // Catch:{ all -> 0x0139 }
            r1.L$5 = r7     // Catch:{ all -> 0x0139 }
            r1.L$6 = r6     // Catch:{ all -> 0x0139 }
            r1.L$7 = r3     // Catch:{ all -> 0x0139 }
            r1.label = r4     // Catch:{ all -> 0x0139 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0139 }
            if (r0 != r9) goto L_0x0114
            return r9
        L_0x0114:
            r16 = r8
            r8 = r1
            r1 = r16
            goto L_0x0056
        L_0x011b:
            kotlin.collections.IndexedValue r13 = new kotlin.collections.IndexedValue     // Catch:{ all -> 0x0139 }
            int r14 = r7.element     // Catch:{ all -> 0x0139 }
            int r15 = r14 + 1
            r7.element = r15     // Catch:{ all -> 0x0139 }
            r13.<init>(r14, r0)     // Catch:{ all -> 0x0139 }
            r6.invoke(r13)     // Catch:{ all -> 0x0139 }
            r0 = r12
            goto L_0x00c5
        L_0x012b:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0139 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0139:
            r0 = move-exception
            goto L_0x013e
        L_0x013b:
            r0 = move-exception
            r2 = r20
        L_0x013e:
            r1 = r0
        L_0x013f:
            throw r1     // Catch:{ all -> 0x0140 }
        L_0x0140:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x014c:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeEachIndexed(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00d4 A[Catch:{ all -> 0x0147 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00eb A[Catch:{ all -> 0x0147 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object elementAt(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r19, int r20, kotlin.coroutines.Continuation<? super E> r21) {
        /*
            r0 = r20
            r1 = r21
            boolean r2 = r1 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAt$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAt$1 r2 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAt$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAt$1 r2 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAt$1
            r2.<init>(r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 46
            java.lang.String r6 = "ReceiveChannel doesn't contain element at index "
            r7 = 2
            r8 = 1
            if (r4 == 0) goto L_0x00a2
            if (r4 == r8) goto L_0x0071
            if (r4 != r7) goto L_0x0069
            java.lang.Object r0 = r2.L$5
            kotlinx.coroutines.channels.ChannelIterator r0 = (kotlinx.coroutines.channels.ChannelIterator) r0
            int r4 = r2.I$1
            java.lang.Object r9 = r2.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r2.L$3
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            java.lang.Object r11 = r2.L$2
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r2.L$1
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            int r13 = r2.I$0
            java.lang.Object r14 = r2.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r1 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009d }
            if (r15 != 0) goto L_0x0064
            r16 = r14
            r14 = r0
            r0 = r16
            r17 = r11
            r11 = r2
            r2 = r17
            r18 = r13
            r13 = r3
            r3 = r9
            r9 = r4
            r4 = r18
            goto L_0x0115
        L_0x0064:
            kotlin.Result$Failure r1 = (kotlin.Result.Failure) r1     // Catch:{ all -> 0x009d }
            java.lang.Throwable r0 = r1.exception     // Catch:{ all -> 0x009d }
            throw r0     // Catch:{ all -> 0x009d }
        L_0x0069:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0071:
            java.lang.Object r0 = r2.L$5
            kotlinx.coroutines.channels.ChannelIterator r0 = (kotlinx.coroutines.channels.ChannelIterator) r0
            int r4 = r2.I$1
            java.lang.Object r9 = r2.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r2.L$3
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            java.lang.Object r11 = r2.L$2
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r2.L$1
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            int r13 = r2.I$0
            java.lang.Object r14 = r2.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r1 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009d }
            if (r15 != 0) goto L_0x0098
            r16 = r10
            r10 = r2
            r2 = r11
            r11 = r16
            goto L_0x00e3
        L_0x0098:
            kotlin.Result$Failure r1 = (kotlin.Result.Failure) r1     // Catch:{ all -> 0x009d }
            java.lang.Throwable r0 = r1.exception     // Catch:{ all -> 0x009d }
            throw r0     // Catch:{ all -> 0x009d }
        L_0x009d:
            r0 = move-exception
            r1 = r0
            r2 = r11
            goto L_0x0171
        L_0x00a2:
            boolean r4 = r1 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L_0x0178
            r1 = 0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            if (r0 < 0) goto L_0x014e
            r4 = 0
            kotlinx.coroutines.channels.ChannelIterator r9 = r19.iterator()     // Catch:{ all -> 0x0149 }
            r4 = r0
            r10 = r2
            r11 = r3
            r13 = r9
            r12 = 0
            r0 = r19
            r2 = r0
            r3 = r2
            r9 = r1
            r1 = r3
        L_0x00bb:
            r10.L$0 = r0     // Catch:{ all -> 0x0147 }
            r10.I$0 = r4     // Catch:{ all -> 0x0147 }
            r10.L$1 = r1     // Catch:{ all -> 0x0147 }
            r10.L$2 = r2     // Catch:{ all -> 0x0147 }
            r10.L$3 = r9     // Catch:{ all -> 0x0147 }
            r10.L$4 = r3     // Catch:{ all -> 0x0147 }
            r10.I$1 = r12     // Catch:{ all -> 0x0147 }
            r10.L$5 = r13     // Catch:{ all -> 0x0147 }
            r10.label = r8     // Catch:{ all -> 0x0147 }
            java.lang.Object r14 = r13.hasNext(r10)     // Catch:{ all -> 0x0147 }
            if (r14 != r11) goto L_0x00d4
            return r11
        L_0x00d4:
            r16 = r14
            r14 = r0
            r0 = r13
            r13 = r4
            r4 = r12
            r12 = r1
            r1 = r16
            r17 = r9
            r9 = r3
            r3 = r11
            r11 = r17
        L_0x00e3:
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x0147 }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x0147 }
            if (r1 == 0) goto L_0x0124
            r10.L$0 = r14     // Catch:{ all -> 0x0147 }
            r10.I$0 = r13     // Catch:{ all -> 0x0147 }
            r10.L$1 = r12     // Catch:{ all -> 0x0147 }
            r10.L$2 = r2     // Catch:{ all -> 0x0147 }
            r10.L$3 = r11     // Catch:{ all -> 0x0147 }
            r10.L$4 = r9     // Catch:{ all -> 0x0147 }
            r10.I$1 = r4     // Catch:{ all -> 0x0147 }
            r10.L$5 = r0     // Catch:{ all -> 0x0147 }
            r10.label = r7     // Catch:{ all -> 0x0147 }
            java.lang.Object r1 = r0.next(r10)     // Catch:{ all -> 0x0147 }
            if (r1 != r3) goto L_0x0104
            return r3
        L_0x0104:
            r16 = r14
            r14 = r0
            r0 = r16
            r17 = r13
            r13 = r3
            r3 = r9
            r9 = r4
            r4 = r17
            r18 = r11
            r11 = r10
            r10 = r18
        L_0x0115:
            int r15 = r9 + 1
            if (r4 != r9) goto L_0x011d
            r2.cancel(r10)
            return r1
        L_0x011d:
            r9 = r10
            r10 = r11
            r1 = r12
            r11 = r13
            r13 = r14
            r12 = r15
            goto L_0x00bb
        L_0x0124:
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r13)     // Catch:{ all -> 0x0147 }
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ all -> 0x0147 }
            r0.intValue()     // Catch:{ all -> 0x0147 }
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException     // Catch:{ all -> 0x0147 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0147 }
            r1.<init>()     // Catch:{ all -> 0x0147 }
            r1.append(r6)     // Catch:{ all -> 0x0147 }
            r1.append(r13)     // Catch:{ all -> 0x0147 }
            r1.append(r5)     // Catch:{ all -> 0x0147 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0147 }
            r0.<init>(r1)     // Catch:{ all -> 0x0147 }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x0147 }
            throw r0     // Catch:{ all -> 0x0147 }
        L_0x0147:
            r0 = move-exception
            goto L_0x014c
        L_0x0149:
            r0 = move-exception
            r2 = r19
        L_0x014c:
            r1 = r0
            goto L_0x0171
        L_0x014e:
            java.lang.Integer r1 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r20)     // Catch:{ all -> 0x0149 }
            java.lang.Number r1 = (java.lang.Number) r1     // Catch:{ all -> 0x0149 }
            r1.intValue()     // Catch:{ all -> 0x0149 }
            java.lang.IndexOutOfBoundsException r1 = new java.lang.IndexOutOfBoundsException     // Catch:{ all -> 0x0149 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0149 }
            r2.<init>()     // Catch:{ all -> 0x0149 }
            r2.append(r6)     // Catch:{ all -> 0x0149 }
            r2.append(r0)     // Catch:{ all -> 0x0149 }
            r2.append(r5)     // Catch:{ all -> 0x0149 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x0149 }
            r1.<init>(r0)     // Catch:{ all -> 0x0149 }
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch:{ all -> 0x0149 }
            throw r1     // Catch:{ all -> 0x0149 }
        L_0x0171:
            throw r1     // Catch:{ all -> 0x0172 }
        L_0x0172:
            r0 = move-exception
            r3 = r0
            r2.cancel(r1)
            throw r3
        L_0x0178:
            kotlin.Result$Failure r1 = (kotlin.Result.Failure) r1
            java.lang.Throwable r0 = r1.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.elementAt(kotlinx.coroutines.channels.ReceiveChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00d2 A[Catch:{ all -> 0x012e }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00e4 A[Catch:{ all -> 0x012e }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object elementAtOrElse(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, int r13, kotlin.jvm.functions.Function1<? super java.lang.Integer, ? extends E> r14, kotlin.coroutines.Continuation<? super E> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrElse$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrElse$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrElse$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrElse$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrElse$1
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x008f
            if (r2 == r4) goto L_0x0060
            if (r2 != r3) goto L_0x0058
            java.lang.Object r12 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            int r13 = r0.I$1
            java.lang.Object r14 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$3
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008b }
            if (r9 != 0) goto L_0x0053
            r10 = r8
            r8 = r12
            r12 = r10
            r11 = r2
            r2 = r0
            r0 = r6
            r6 = r1
            r1 = r11
            goto L_0x0107
        L_0x0053:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x008b }
            java.lang.Throwable r12 = r15.exception     // Catch:{ all -> 0x008b }
            throw r12     // Catch:{ all -> 0x008b }
        L_0x0058:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0060:
            java.lang.Object r12 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            int r13 = r0.I$1
            java.lang.Object r14 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$3
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008b }
            if (r9 != 0) goto L_0x0086
            r10 = r6
            r6 = r13
            r13 = r5
            r5 = r10
            goto L_0x00dc
        L_0x0086:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x008b }
            java.lang.Throwable r12 = r15.exception     // Catch:{ all -> 0x008b }
            throw r12     // Catch:{ all -> 0x008b }
        L_0x008b:
            r12 = move-exception
            r13 = r5
            goto L_0x0134
        L_0x008f:
            boolean r2 = r15 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x0140
            r15 = 0
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            if (r13 >= 0) goto L_0x00ab
            java.lang.Integer r13 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r13)     // Catch:{ all -> 0x0130 }
            java.lang.Object r13 = r14.invoke(r13)     // Catch:{ all -> 0x0130 }
            r14 = 4
            kotlin.jvm.internal.InlineMarker.finallyStart(r14)
            r12.cancel(r15)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r14)
            return r13
        L_0x00ab:
            r2 = 0
            kotlinx.coroutines.channels.ChannelIterator r5 = r12.iterator()     // Catch:{ all -> 0x0130 }
            r2 = r0
            r7 = r5
            r6 = 0
            r0 = r14
            r5 = r1
            r14 = r12
            r1 = r15
            r15 = r13
            r13 = r14
        L_0x00b9:
            r2.L$0 = r12     // Catch:{ all -> 0x012e }
            r2.I$0 = r15     // Catch:{ all -> 0x012e }
            r2.L$1 = r0     // Catch:{ all -> 0x012e }
            r2.L$2 = r13     // Catch:{ all -> 0x012e }
            r2.L$3 = r1     // Catch:{ all -> 0x012e }
            r2.L$4 = r14     // Catch:{ all -> 0x012e }
            r2.I$1 = r6     // Catch:{ all -> 0x012e }
            r2.L$5 = r7     // Catch:{ all -> 0x012e }
            r2.label = r4     // Catch:{ all -> 0x012e }
            java.lang.Object r8 = r7.hasNext(r2)     // Catch:{ all -> 0x012e }
            if (r8 != r5) goto L_0x00d2
            return r5
        L_0x00d2:
            r10 = r8
            r8 = r12
            r12 = r7
            r7 = r15
            r15 = r10
            r11 = r5
            r5 = r0
            r0 = r2
            r2 = r1
            r1 = r11
        L_0x00dc:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ all -> 0x012e }
            boolean r15 = r15.booleanValue()     // Catch:{ all -> 0x012e }
            if (r15 == 0) goto L_0x011c
            r0.L$0 = r8     // Catch:{ all -> 0x012e }
            r0.I$0 = r7     // Catch:{ all -> 0x012e }
            r0.L$1 = r5     // Catch:{ all -> 0x012e }
            r0.L$2 = r13     // Catch:{ all -> 0x012e }
            r0.L$3 = r2     // Catch:{ all -> 0x012e }
            r0.L$4 = r14     // Catch:{ all -> 0x012e }
            r0.I$1 = r6     // Catch:{ all -> 0x012e }
            r0.L$5 = r12     // Catch:{ all -> 0x012e }
            r0.label = r3     // Catch:{ all -> 0x012e }
            java.lang.Object r15 = r12.next(r0)     // Catch:{ all -> 0x012e }
            if (r15 != r1) goto L_0x00fd
            return r1
        L_0x00fd:
            r10 = r8
            r8 = r12
            r12 = r10
            r11 = r5
            r5 = r13
            r13 = r6
            r6 = r1
            r1 = r2
            r2 = r0
            r0 = r11
        L_0x0107:
            int r9 = r13 + 1
            if (r7 != r13) goto L_0x0116
            r12 = 3
            kotlin.jvm.internal.InlineMarker.finallyStart(r12)
            r5.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r12)
            return r15
        L_0x0116:
            r13 = r5
            r5 = r6
            r15 = r7
            r7 = r8
            r6 = r9
            goto L_0x00b9
        L_0x011c:
            java.lang.Integer r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)     // Catch:{ all -> 0x012e }
            java.lang.Object r12 = r5.invoke(r12)     // Catch:{ all -> 0x012e }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            r13.cancel(r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r12
        L_0x012e:
            r12 = move-exception
            goto L_0x0134
        L_0x0130:
            r13 = move-exception
            r10 = r13
            r13 = r12
            r12 = r10
        L_0x0134:
            throw r12     // Catch:{ all -> 0x0135 }
        L_0x0135:
            r14 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            r13.cancel(r12)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r14
        L_0x0140:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15
            java.lang.Throwable r12 = r15.exception
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.elementAtOrElse(kotlinx.coroutines.channels.ReceiveChannel, int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00cb A[Catch:{ all -> 0x00ff }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object elementAtOrNull(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, int r14, kotlin.coroutines.Continuation<? super E> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrNull$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrNull$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$elementAtOrNull$1
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L_0x0087
            if (r2 == r5) goto L_0x005d
            if (r2 != r3) goto L_0x0055
            java.lang.Object r13 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            int r14 = r0.I$1
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r6 = r0.L$2
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            int r8 = r0.I$0
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            boolean r10 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0083 }
            if (r10 != 0) goto L_0x0050
            r11 = r9
            r9 = r13
            r13 = r11
            r12 = r6
            r6 = r0
            r0 = r8
            r8 = r1
            r1 = r12
            goto L_0x00ea
        L_0x0050:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x0083 }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x0083 }
            throw r13     // Catch:{ all -> 0x0083 }
        L_0x0055:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x005d:
            java.lang.Object r13 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            int r14 = r0.I$1
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r6 = r0.L$2
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            int r8 = r0.I$0
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            boolean r10 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0083 }
            if (r10 != 0) goto L_0x007e
            r11 = r8
            r8 = r13
            r13 = r7
            r7 = r11
            goto L_0x00c3
        L_0x007e:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x0083 }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x0083 }
            throw r13     // Catch:{ all -> 0x0083 }
        L_0x0083:
            r13 = move-exception
            r14 = r7
            goto L_0x0103
        L_0x0087:
            boolean r2 = r15 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x0109
            r15 = r4
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            if (r14 >= 0) goto L_0x0094
            r13.cancel(r15)
            return r4
        L_0x0094:
            r2 = 0
            kotlinx.coroutines.channels.ChannelIterator r6 = r13.iterator()     // Catch:{ all -> 0x00ff }
            r2 = r0
            r8 = r6
            r7 = 0
            r0 = r14
            r6 = r1
            r14 = r13
            r1 = r15
            r15 = r14
        L_0x00a1:
            r2.L$0 = r13     // Catch:{ all -> 0x00fd }
            r2.I$0 = r0     // Catch:{ all -> 0x00fd }
            r2.L$1 = r14     // Catch:{ all -> 0x00fd }
            r2.L$2 = r1     // Catch:{ all -> 0x00fd }
            r2.L$3 = r15     // Catch:{ all -> 0x00fd }
            r2.I$1 = r7     // Catch:{ all -> 0x00fd }
            r2.L$4 = r8     // Catch:{ all -> 0x00fd }
            r2.label = r5     // Catch:{ all -> 0x00fd }
            java.lang.Object r9 = r8.hasNext(r2)     // Catch:{ all -> 0x00fd }
            if (r9 != r6) goto L_0x00b8
            return r6
        L_0x00b8:
            r11 = r9
            r9 = r13
            r13 = r14
            r14 = r7
            r7 = r0
            r0 = r2
            r2 = r15
            r15 = r11
            r12 = r6
            r6 = r1
            r1 = r12
        L_0x00c3:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ all -> 0x00ff }
            boolean r15 = r15.booleanValue()     // Catch:{ all -> 0x00ff }
            if (r15 == 0) goto L_0x00f9
            r0.L$0 = r9     // Catch:{ all -> 0x00ff }
            r0.I$0 = r7     // Catch:{ all -> 0x00ff }
            r0.L$1 = r13     // Catch:{ all -> 0x00ff }
            r0.L$2 = r6     // Catch:{ all -> 0x00ff }
            r0.L$3 = r2     // Catch:{ all -> 0x00ff }
            r0.I$1 = r14     // Catch:{ all -> 0x00ff }
            r0.L$4 = r8     // Catch:{ all -> 0x00ff }
            r0.label = r3     // Catch:{ all -> 0x00ff }
            java.lang.Object r15 = r8.next(r0)     // Catch:{ all -> 0x00ff }
            if (r15 != r1) goto L_0x00e2
            return r1
        L_0x00e2:
            r11 = r7
            r7 = r13
            r13 = r9
            r9 = r8
            r8 = r1
            r1 = r6
            r6 = r0
            r0 = r11
        L_0x00ea:
            int r10 = r14 + 1
            if (r0 != r14) goto L_0x00f2
            r7.cancel(r1)
            return r15
        L_0x00f2:
            r15 = r2
            r2 = r6
            r14 = r7
            r6 = r8
            r8 = r9
            r7 = r10
            goto L_0x00a1
        L_0x00f9:
            r13.cancel(r6)
            return r4
        L_0x00fd:
            r13 = move-exception
            goto L_0x0103
        L_0x00ff:
            r14 = move-exception
            r11 = r14
            r14 = r13
            r13 = r11
        L_0x0103:
            throw r13     // Catch:{ all -> 0x0104 }
        L_0x0104:
            r15 = move-exception
            r14.cancel(r13)
            throw r15
        L_0x0109:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15
            java.lang.Throwable r13 = r15.exception
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.elementAtOrNull(kotlinx.coroutines.channels.ReceiveChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ce A[Catch:{ all -> 0x012c }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00e5 A[Catch:{ all -> 0x012c }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object find(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r19, kotlin.coroutines.Continuation<? super E> r20) {
        /*
            r0 = r20
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$find$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$find$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$find$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$find$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$find$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 0
            r5 = 2
            r6 = 1
            if (r3 == 0) goto L_0x009e
            if (r3 == r6) goto L_0x006b
            if (r3 != r5) goto L_0x0063
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0099 }
            if (r14 != 0) goto L_0x005e
            r15 = r10
            r10 = r1
            r1 = r11
            r11 = r2
            r2 = r15
            r16 = r12
            r12 = r3
            r3 = r9
        L_0x0059:
            r9 = r8
            r8 = r16
            goto L_0x0107
        L_0x005e:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x0099 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x0099 }
            throw r0     // Catch:{ all -> 0x0099 }
        L_0x0063:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006b:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0099 }
            if (r14 != 0) goto L_0x0094
            r15 = r12
            r12 = r3
            r3 = r9
            r9 = r15
            goto L_0x00dd
        L_0x0094:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x0099 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x0099 }
            throw r0     // Catch:{ all -> 0x0099 }
        L_0x0099:
            r0 = move-exception
            r1 = r0
            r3 = r9
            goto L_0x0132
        L_0x009e:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x013f
            r0 = r4
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlinx.coroutines.channels.ChannelIterator r3 = r18.iterator()     // Catch:{ all -> 0x012e }
            r7 = r18
            r8 = r19
            r9 = r0
            r10 = r1
            r11 = r2
            r12 = r3
            r0 = r7
            r1 = r0
            r2 = r1
            r3 = r2
        L_0x00b5:
            r10.L$0 = r0     // Catch:{ all -> 0x012c }
            r10.L$1 = r8     // Catch:{ all -> 0x012c }
            r10.L$2 = r1     // Catch:{ all -> 0x012c }
            r10.L$3 = r2     // Catch:{ all -> 0x012c }
            r10.L$4 = r3     // Catch:{ all -> 0x012c }
            r10.L$5 = r9     // Catch:{ all -> 0x012c }
            r10.L$6 = r7     // Catch:{ all -> 0x012c }
            r10.L$7 = r12     // Catch:{ all -> 0x012c }
            r10.label = r6     // Catch:{ all -> 0x012c }
            java.lang.Object r13 = r12.hasNext(r10)     // Catch:{ all -> 0x012c }
            if (r13 != r11) goto L_0x00ce
            return r11
        L_0x00ce:
            r15 = r13
            r13 = r0
            r0 = r15
            r16 = r11
            r11 = r1
            r1 = r10
            r10 = r2
            r2 = r16
            r17 = r9
            r9 = r8
            r8 = r17
        L_0x00dd:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x012c }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x012c }
            if (r0 == 0) goto L_0x0120
            r1.L$0 = r13     // Catch:{ all -> 0x012c }
            r1.L$1 = r9     // Catch:{ all -> 0x012c }
            r1.L$2 = r11     // Catch:{ all -> 0x012c }
            r1.L$3 = r10     // Catch:{ all -> 0x012c }
            r1.L$4 = r3     // Catch:{ all -> 0x012c }
            r1.L$5 = r8     // Catch:{ all -> 0x012c }
            r1.L$6 = r7     // Catch:{ all -> 0x012c }
            r1.L$7 = r12     // Catch:{ all -> 0x012c }
            r1.label = r5     // Catch:{ all -> 0x012c }
            java.lang.Object r0 = r12.next(r1)     // Catch:{ all -> 0x012c }
            if (r0 != r2) goto L_0x00fe
            return r2
        L_0x00fe:
            r15 = r10
            r10 = r1
            r1 = r11
            r11 = r2
            r2 = r15
            r16 = r9
            goto L_0x0059
        L_0x0107:
            java.lang.Object r14 = r8.invoke(r0)     // Catch:{ all -> 0x012c }
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ all -> 0x012c }
            boolean r14 = r14.booleanValue()     // Catch:{ all -> 0x012c }
            if (r14 == 0) goto L_0x011e
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r3.cancel(r9)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            r4 = r0
            goto L_0x012b
        L_0x011e:
            r0 = r13
            goto L_0x00b5
        L_0x0120:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x012c }
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            r3.cancel(r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
        L_0x012b:
            return r4
        L_0x012c:
            r0 = move-exception
            goto L_0x0131
        L_0x012e:
            r0 = move-exception
            r3 = r18
        L_0x0131:
            r1 = r0
        L_0x0132:
            throw r1     // Catch:{ all -> 0x0133 }
        L_0x0133:
            r0 = move-exception
            r2 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            r3.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            throw r2
        L_0x013f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.find(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00dd A[Catch:{ all -> 0x0133 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00de A[Catch:{ all -> 0x0133 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f3 A[Catch:{ all -> 0x0133 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0121 A[Catch:{ all -> 0x0133 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object findLast(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r17, kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r18, kotlin.coroutines.Continuation<? super E> r19) {
        /*
            r0 = r19
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$findLast$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$findLast$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$findLast$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$findLast$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$findLast$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00a4
            if (r3 == r5) goto L_0x006c
            if (r3 != r4) goto L_0x0064
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009f }
            if (r14 != 0) goto L_0x005f
            r15 = r9
            r9 = r1
            r1 = r11
            r11 = r7
            r7 = r12
            r12 = r3
            r3 = r8
        L_0x005a:
            r8 = r10
            r10 = r2
            r2 = r15
            goto L_0x0115
        L_0x005f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009f }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009f }
            throw r0     // Catch:{ all -> 0x009f }
        L_0x0064:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006c:
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009f }
            if (r14 != 0) goto L_0x009a
            r15 = r12
            r12 = r3
            r3 = r8
            r8 = r15
            goto L_0x00eb
        L_0x009a:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009f }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009f }
            throw r0     // Catch:{ all -> 0x009f }
        L_0x009f:
            r0 = move-exception
            r1 = r0
            r3 = r8
            goto L_0x0139
        L_0x00a4:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0146
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r6 = r17.iterator()     // Catch:{ all -> 0x0135 }
            r7 = r18
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r12 = r6
            r0 = r17
            r1 = r0
            r2 = r1
            r3 = r2
            r6 = r3
        L_0x00c3:
            r9.L$0 = r0     // Catch:{ all -> 0x0133 }
            r9.L$1 = r7     // Catch:{ all -> 0x0133 }
            r9.L$2 = r1     // Catch:{ all -> 0x0133 }
            r9.L$3 = r8     // Catch:{ all -> 0x0133 }
            r9.L$4 = r2     // Catch:{ all -> 0x0133 }
            r9.L$5 = r3     // Catch:{ all -> 0x0133 }
            r9.L$6 = r11     // Catch:{ all -> 0x0133 }
            r9.L$7 = r6     // Catch:{ all -> 0x0133 }
            r9.L$8 = r12     // Catch:{ all -> 0x0133 }
            r9.label = r5     // Catch:{ all -> 0x0133 }
            java.lang.Object r13 = r12.hasNext(r9)     // Catch:{ all -> 0x0133 }
            if (r13 != r10) goto L_0x00de
            return r10
        L_0x00de:
            r15 = r13
            r13 = r0
            r0 = r15
            r16 = r11
            r11 = r1
            r1 = r9
            r9 = r2
            r2 = r10
            r10 = r8
            r8 = r7
            r7 = r16
        L_0x00eb:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0133 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0133 }
            if (r0 == 0) goto L_0x0125
            r1.L$0 = r13     // Catch:{ all -> 0x0133 }
            r1.L$1 = r8     // Catch:{ all -> 0x0133 }
            r1.L$2 = r11     // Catch:{ all -> 0x0133 }
            r1.L$3 = r10     // Catch:{ all -> 0x0133 }
            r1.L$4 = r9     // Catch:{ all -> 0x0133 }
            r1.L$5 = r3     // Catch:{ all -> 0x0133 }
            r1.L$6 = r7     // Catch:{ all -> 0x0133 }
            r1.L$7 = r6     // Catch:{ all -> 0x0133 }
            r1.L$8 = r12     // Catch:{ all -> 0x0133 }
            r1.label = r4     // Catch:{ all -> 0x0133 }
            java.lang.Object r0 = r12.next(r1)     // Catch:{ all -> 0x0133 }
            if (r0 != r2) goto L_0x010e
            return r2
        L_0x010e:
            r15 = r9
            r9 = r1
            r1 = r11
            r11 = r7
            r7 = r8
            goto L_0x005a
        L_0x0115:
            java.lang.Object r14 = r7.invoke(r0)     // Catch:{ all -> 0x0133 }
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ all -> 0x0133 }
            boolean r14 = r14.booleanValue()     // Catch:{ all -> 0x0133 }
            if (r14 == 0) goto L_0x0123
            r8.element = r0     // Catch:{ all -> 0x0133 }
        L_0x0123:
            r0 = r13
            goto L_0x00c3
        L_0x0125:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0133 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r3.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            T r0 = r10.element
            return r0
        L_0x0133:
            r0 = move-exception
            goto L_0x0138
        L_0x0135:
            r0 = move-exception
            r3 = r17
        L_0x0138:
            r1 = r0
        L_0x0139:
            throw r1     // Catch:{ all -> 0x013a }
        L_0x013a:
            r0 = move-exception
            r2 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r3.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r2
        L_0x0146:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.findLast(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a1 A[Catch:{ all -> 0x0075 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00bc A[SYNTHETIC, Splitter:B:46:0x00bc] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object first(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r9, kotlin.coroutines.Continuation<? super E> r10) {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$1
            r0.<init>(r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0077
            if (r2 == r4) goto L_0x0054
            if (r2 != r3) goto L_0x004c
            java.lang.Object r9 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r9 = (kotlinx.coroutines.channels.ChannelIterator) r9
            java.lang.Object r9 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r9 = r0.L$2
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            java.lang.Object r1 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            boolean r0 = r10 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0048 }
            if (r0 != 0) goto L_0x0043
            goto L_0x00b8
        L_0x0043:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10     // Catch:{ all -> 0x0048 }
            java.lang.Throwable r9 = r10.exception     // Catch:{ all -> 0x0048 }
            throw r9     // Catch:{ all -> 0x0048 }
        L_0x0048:
            r9 = move-exception
            r5 = r1
            goto L_0x00c9
        L_0x004c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0054:
            java.lang.Object r9 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r9 = (kotlinx.coroutines.channels.ChannelIterator) r9
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$2
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            java.lang.Object r5 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            boolean r7 = r10 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0075 }
            if (r7 != 0) goto L_0x0070
            r8 = r4
            r4 = r10
            r10 = r8
            goto L_0x0099
        L_0x0070:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10     // Catch:{ all -> 0x0075 }
            java.lang.Throwable r9 = r10.exception     // Catch:{ all -> 0x0075 }
            throw r9     // Catch:{ all -> 0x0075 }
        L_0x0075:
            r9 = move-exception
            goto L_0x00c9
        L_0x0077:
            boolean r2 = r10 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x00cf
            r10 = 0
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            kotlinx.coroutines.channels.ChannelIterator r2 = r9.iterator()     // Catch:{ all -> 0x00c6 }
            r0.L$0 = r9     // Catch:{ all -> 0x00c6 }
            r0.L$1 = r9     // Catch:{ all -> 0x00c6 }
            r0.L$2 = r10     // Catch:{ all -> 0x00c6 }
            r0.L$3 = r9     // Catch:{ all -> 0x00c6 }
            r0.L$4 = r2     // Catch:{ all -> 0x00c6 }
            r0.label = r4     // Catch:{ all -> 0x00c6 }
            java.lang.Object r4 = r2.hasNext(r0)     // Catch:{ all -> 0x00c6 }
            if (r4 != r1) goto L_0x0095
            return r1
        L_0x0095:
            r5 = r9
            r6 = r5
            r9 = r2
            r2 = r6
        L_0x0099:
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0075 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0075 }
            if (r4 == 0) goto L_0x00bc
            r0.L$0 = r6     // Catch:{ all -> 0x0075 }
            r0.L$1 = r5     // Catch:{ all -> 0x0075 }
            r0.L$2 = r10     // Catch:{ all -> 0x0075 }
            r0.L$3 = r2     // Catch:{ all -> 0x0075 }
            r0.L$4 = r9     // Catch:{ all -> 0x0075 }
            r0.label = r3     // Catch:{ all -> 0x0075 }
            java.lang.Object r9 = r9.next(r0)     // Catch:{ all -> 0x0075 }
            if (r9 != r1) goto L_0x00b4
            return r1
        L_0x00b4:
            r1 = r5
            r8 = r10
            r10 = r9
            r9 = r8
        L_0x00b8:
            r1.cancel(r9)
            return r10
        L_0x00bc:
            java.util.NoSuchElementException r9 = new java.util.NoSuchElementException     // Catch:{ all -> 0x0075 }
            java.lang.String r10 = "ReceiveChannel is empty."
            r9.<init>(r10)     // Catch:{ all -> 0x0075 }
            java.lang.Throwable r9 = (java.lang.Throwable) r9     // Catch:{ all -> 0x0075 }
            throw r9     // Catch:{ all -> 0x0075 }
        L_0x00c6:
            r10 = move-exception
            r5 = r9
            r9 = r10
        L_0x00c9:
            throw r9     // Catch:{ all -> 0x00ca }
        L_0x00ca:
            r10 = move-exception
            r5.cancel(r9)
            throw r10
        L_0x00cf:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r9 = r10.exception
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.first(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ba A[Catch:{ all -> 0x0118 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ca A[Catch:{ all -> 0x0118 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00fe  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object first(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r14, kotlin.coroutines.Continuation<? super E> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$3
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$first$3
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0090
            if (r2 == r4) goto L_0x0062
            if (r2 != r3) goto L_0x005a
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008c }
            if (r9 != 0) goto L_0x0055
            r10 = r8
            r8 = r13
            r13 = r10
        L_0x004c:
            r11 = r0
            r0 = r14
            r14 = r6
            r6 = r11
            r12 = r7
            r7 = r1
            r1 = r12
            goto L_0x00e8
        L_0x0055:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x008c }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x008c }
            throw r13     // Catch:{ all -> 0x008c }
        L_0x005a:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0062:
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008c }
            if (r9 != 0) goto L_0x0087
            r10 = r8
            r8 = r15
            r15 = r5
            r5 = r10
            goto L_0x00c2
        L_0x0087:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x008c }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x008c }
            throw r13     // Catch:{ all -> 0x008c }
        L_0x008c:
            r13 = move-exception
            r15 = r5
            goto L_0x011d
        L_0x0090:
            boolean r2 = r15 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x0129
            r15 = 0
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            kotlinx.coroutines.channels.ChannelIterator r2 = r13.iterator()     // Catch:{ all -> 0x011a }
            r5 = r0
            r6 = r1
            r7 = r2
            r0 = r13
            r1 = r14
            r2 = r15
            r14 = r0
            r15 = r14
        L_0x00a3:
            r5.L$0 = r13     // Catch:{ all -> 0x0118 }
            r5.L$1 = r1     // Catch:{ all -> 0x0118 }
            r5.L$2 = r14     // Catch:{ all -> 0x0118 }
            r5.L$3 = r15     // Catch:{ all -> 0x0118 }
            r5.L$4 = r2     // Catch:{ all -> 0x0118 }
            r5.L$5 = r0     // Catch:{ all -> 0x0118 }
            r5.L$6 = r7     // Catch:{ all -> 0x0118 }
            r5.label = r4     // Catch:{ all -> 0x0118 }
            java.lang.Object r8 = r7.hasNext(r5)     // Catch:{ all -> 0x0118 }
            if (r8 != r6) goto L_0x00ba
            return r6
        L_0x00ba:
            r10 = r5
            r5 = r13
            r13 = r7
            r7 = r1
            r1 = r6
            r6 = r14
            r14 = r0
            r0 = r10
        L_0x00c2:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x0118 }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x0118 }
            if (r8 == 0) goto L_0x0103
            r0.L$0 = r5     // Catch:{ all -> 0x0118 }
            r0.L$1 = r7     // Catch:{ all -> 0x0118 }
            r0.L$2 = r6     // Catch:{ all -> 0x0118 }
            r0.L$3 = r15     // Catch:{ all -> 0x0118 }
            r0.L$4 = r2     // Catch:{ all -> 0x0118 }
            r0.L$5 = r14     // Catch:{ all -> 0x0118 }
            r0.L$6 = r13     // Catch:{ all -> 0x0118 }
            r0.label = r3     // Catch:{ all -> 0x0118 }
            java.lang.Object r8 = r13.next(r0)     // Catch:{ all -> 0x0118 }
            if (r8 != r1) goto L_0x00e1
            return r1
        L_0x00e1:
            r10 = r8
            r8 = r13
            r13 = r5
            r5 = r15
            r15 = r10
            goto L_0x004c
        L_0x00e8:
            java.lang.Object r9 = r1.invoke(r15)     // Catch:{ all -> 0x008c }
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ all -> 0x008c }
            boolean r9 = r9.booleanValue()     // Catch:{ all -> 0x008c }
            if (r9 == 0) goto L_0x00fe
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            r5.cancel(r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r15
        L_0x00fe:
            r15 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            goto L_0x00a3
        L_0x0103:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0118 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            r15.cancel(r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            java.util.NoSuchElementException r13 = new java.util.NoSuchElementException
            java.lang.String r14 = "ReceiveChannel contains no element matching the predicate."
            r13.<init>(r14)
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            throw r13
        L_0x0118:
            r13 = move-exception
            goto L_0x011d
        L_0x011a:
            r14 = move-exception
            r15 = r13
            r13 = r14
        L_0x011d:
            throw r13     // Catch:{ all -> 0x011e }
        L_0x011e:
            r14 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            r15.cancel(r13)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r14
        L_0x0129:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15
            java.lang.Throwable r13 = r15.exception
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.first(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a6 A[SYNTHETIC, Splitter:B:42:0x00a6] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object firstOrNull(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, kotlin.coroutines.Continuation<? super E> r11) {
        /*
            boolean r0 = r11 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$1
            r0.<init>(r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0078
            if (r2 == r5) goto L_0x0055
            if (r2 != r4) goto L_0x004d
            java.lang.Object r10 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r10 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r10 = r0.L$2
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            java.lang.Object r1 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            boolean r0 = r11 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0049 }
            if (r0 != 0) goto L_0x0044
            goto L_0x00bd
        L_0x0044:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11     // Catch:{ all -> 0x0049 }
            java.lang.Throwable r10 = r11.exception     // Catch:{ all -> 0x0049 }
            throw r10     // Catch:{ all -> 0x0049 }
        L_0x0049:
            r10 = move-exception
            r6 = r1
            goto L_0x00c4
        L_0x004d:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0055:
            java.lang.Object r10 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$2
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            boolean r8 = r11 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0076 }
            if (r8 != 0) goto L_0x0071
            r9 = r5
            r5 = r11
            r11 = r9
            goto L_0x009a
        L_0x0071:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11     // Catch:{ all -> 0x0076 }
            java.lang.Throwable r10 = r11.exception     // Catch:{ all -> 0x0076 }
            throw r10     // Catch:{ all -> 0x0076 }
        L_0x0076:
            r10 = move-exception
            goto L_0x00c4
        L_0x0078:
            boolean r2 = r11 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x00ca
            r11 = r3
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            kotlinx.coroutines.channels.ChannelIterator r2 = r10.iterator()     // Catch:{ all -> 0x00c1 }
            r0.L$0 = r10     // Catch:{ all -> 0x00c1 }
            r0.L$1 = r10     // Catch:{ all -> 0x00c1 }
            r0.L$2 = r11     // Catch:{ all -> 0x00c1 }
            r0.L$3 = r10     // Catch:{ all -> 0x00c1 }
            r0.L$4 = r2     // Catch:{ all -> 0x00c1 }
            r0.label = r5     // Catch:{ all -> 0x00c1 }
            java.lang.Object r5 = r2.hasNext(r0)     // Catch:{ all -> 0x00c1 }
            if (r5 != r1) goto L_0x0096
            return r1
        L_0x0096:
            r6 = r10
            r7 = r6
            r10 = r2
            r2 = r7
        L_0x009a:
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0076 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0076 }
            if (r5 != 0) goto L_0x00a6
            r6.cancel(r11)
            return r3
        L_0x00a6:
            r0.L$0 = r7     // Catch:{ all -> 0x0076 }
            r0.L$1 = r6     // Catch:{ all -> 0x0076 }
            r0.L$2 = r11     // Catch:{ all -> 0x0076 }
            r0.L$3 = r2     // Catch:{ all -> 0x0076 }
            r0.L$4 = r10     // Catch:{ all -> 0x0076 }
            r0.label = r4     // Catch:{ all -> 0x0076 }
            java.lang.Object r10 = r10.next(r0)     // Catch:{ all -> 0x0076 }
            if (r10 != r1) goto L_0x00b9
            return r1
        L_0x00b9:
            r1 = r6
            r9 = r11
            r11 = r10
            r10 = r9
        L_0x00bd:
            r1.cancel(r10)
            return r11
        L_0x00c1:
            r11 = move-exception
            r6 = r10
            r10 = r11
        L_0x00c4:
            throw r10     // Catch:{ all -> 0x00c5 }
        L_0x00c5:
            r11 = move-exception
            r6.cancel(r10)
            throw r11
        L_0x00ca:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r10 = r11.exception
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.firstOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c0 A[Catch:{ all -> 0x0114 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d4 A[Catch:{ all -> 0x0114 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object firstOrNull(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r17, kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r18, kotlin.coroutines.Continuation<? super E> r19) {
        /*
            r0 = r19
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$3 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$3 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$firstOrNull$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 0
            r5 = 2
            r6 = 1
            if (r3 == 0) goto L_0x0093
            if (r3 == r6) goto L_0x0064
            if (r3 != r5) goto L_0x005c
            java.lang.Object r3 = r1.L$6
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$4
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008e }
            if (r13 != 0) goto L_0x0057
            r14 = r9
            r9 = r1
            r1 = r10
            r10 = r2
            r2 = r14
        L_0x0051:
            r15 = r11
            r11 = r3
            r3 = r7
            r7 = r15
            goto L_0x00f0
        L_0x0057:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x008e }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x008e }
            throw r0     // Catch:{ all -> 0x008e }
        L_0x005c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0064:
            java.lang.Object r3 = r1.L$6
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$4
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008e }
            if (r13 != 0) goto L_0x0089
            r14 = r10
            r10 = r2
            r2 = r9
            r9 = r14
            goto L_0x00cc
        L_0x0089:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x008e }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x008e }
            throw r0     // Catch:{ all -> 0x008e }
        L_0x008e:
            r0 = move-exception
            r1 = r0
            r2 = r9
            goto L_0x011a
        L_0x0093:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0127
            r0 = r4
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlinx.coroutines.channels.ChannelIterator r3 = r17.iterator()     // Catch:{ all -> 0x0116 }
            r7 = r18
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r0 = r17
            r1 = r0
            r2 = r1
            r3 = r2
        L_0x00a9:
            r9.L$0 = r0     // Catch:{ all -> 0x0114 }
            r9.L$1 = r7     // Catch:{ all -> 0x0114 }
            r9.L$2 = r1     // Catch:{ all -> 0x0114 }
            r9.L$3 = r2     // Catch:{ all -> 0x0114 }
            r9.L$4 = r8     // Catch:{ all -> 0x0114 }
            r9.L$5 = r3     // Catch:{ all -> 0x0114 }
            r9.L$6 = r11     // Catch:{ all -> 0x0114 }
            r9.label = r6     // Catch:{ all -> 0x0114 }
            java.lang.Object r12 = r11.hasNext(r9)     // Catch:{ all -> 0x0114 }
            if (r12 != r10) goto L_0x00c0
            return r10
        L_0x00c0:
            r14 = r12
            r12 = r0
            r0 = r14
            r15 = r9
            r9 = r1
            r1 = r15
            r16 = r7
            r7 = r3
            r3 = r11
            r11 = r16
        L_0x00cc:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0114 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0114 }
            if (r0 == 0) goto L_0x0108
            r1.L$0 = r12     // Catch:{ all -> 0x0114 }
            r1.L$1 = r11     // Catch:{ all -> 0x0114 }
            r1.L$2 = r9     // Catch:{ all -> 0x0114 }
            r1.L$3 = r2     // Catch:{ all -> 0x0114 }
            r1.L$4 = r8     // Catch:{ all -> 0x0114 }
            r1.L$5 = r7     // Catch:{ all -> 0x0114 }
            r1.L$6 = r3     // Catch:{ all -> 0x0114 }
            r1.label = r5     // Catch:{ all -> 0x0114 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0114 }
            if (r0 != r10) goto L_0x00eb
            return r10
        L_0x00eb:
            r14 = r9
            r9 = r1
            r1 = r14
            goto L_0x0051
        L_0x00f0:
            java.lang.Object r13 = r7.invoke(r0)     // Catch:{ all -> 0x0114 }
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ all -> 0x0114 }
            boolean r13 = r13.booleanValue()     // Catch:{ all -> 0x0114 }
            if (r13 == 0) goto L_0x0106
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            return r0
        L_0x0106:
            r0 = r12
            goto L_0x00a9
        L_0x0108:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0114 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            r2.cancel(r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            return r4
        L_0x0114:
            r0 = move-exception
            goto L_0x0119
        L_0x0116:
            r0 = move-exception
            r2 = r17
        L_0x0119:
            r1 = r0
        L_0x011a:
            throw r1     // Catch:{ all -> 0x011b }
        L_0x011b:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            throw r3
        L_0x0127:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.firstOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d4 A[Catch:{ all -> 0x012d }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ed A[Catch:{ all -> 0x012d }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x011b A[SYNTHETIC, Splitter:B:51:0x011b] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object indexOf(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, E r19, kotlin.coroutines.Continuation<? super java.lang.Integer> r20) {
        /*
            r0 = r20
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOf$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOf$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOf$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOf$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOf$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x009c
            if (r3 == r5) goto L_0x006a
            if (r3 != r4) goto L_0x0062
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$1
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0097 }
            if (r13 != 0) goto L_0x005d
            r14 = r8
            r8 = r1
            r1 = r9
            r9 = r2
            r2 = r14
        L_0x0052:
            r15 = r11
            r11 = r3
            r3 = r6
            r6 = r15
            r16 = r10
            r10 = r7
            r7 = r16
            goto L_0x010b
        L_0x005d:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x0097 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x0097 }
            throw r0     // Catch:{ all -> 0x0097 }
        L_0x0062:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006a:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$1
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0097 }
            if (r13 != 0) goto L_0x0092
            r14 = r9
            r9 = r2
            r2 = r8
            r8 = r14
            goto L_0x00e5
        L_0x0092:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x0097 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x0097 }
            throw r0     // Catch:{ all -> 0x0097 }
        L_0x0097:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x0133
        L_0x009c:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x013a
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r6 = r18.iterator()     // Catch:{ all -> 0x012f }
            r7 = r0
            r8 = r1
            r9 = r2
            r10 = r3
            r11 = r6
            r0 = r18
            r1 = r0
            r2 = r1
            r3 = r2
            r6 = r19
        L_0x00bb:
            r8.L$0 = r0     // Catch:{ all -> 0x012d }
            r8.L$1 = r6     // Catch:{ all -> 0x012d }
            r8.L$2 = r7     // Catch:{ all -> 0x012d }
            r8.L$3 = r1     // Catch:{ all -> 0x012d }
            r8.L$4 = r2     // Catch:{ all -> 0x012d }
            r8.L$5 = r10     // Catch:{ all -> 0x012d }
            r8.L$6 = r3     // Catch:{ all -> 0x012d }
            r8.L$7 = r11     // Catch:{ all -> 0x012d }
            r8.label = r5     // Catch:{ all -> 0x012d }
            java.lang.Object r12 = r11.hasNext(r8)     // Catch:{ all -> 0x012d }
            if (r12 != r9) goto L_0x00d4
            return r9
        L_0x00d4:
            r14 = r12
            r12 = r0
            r0 = r14
            r15 = r8
            r8 = r1
            r1 = r15
            r16 = r6
            r6 = r3
            r3 = r11
            r11 = r16
            r17 = r10
            r10 = r7
            r7 = r17
        L_0x00e5:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x012d }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x012d }
            if (r0 == 0) goto L_0x0122
            r1.L$0 = r12     // Catch:{ all -> 0x012d }
            r1.L$1 = r11     // Catch:{ all -> 0x012d }
            r1.L$2 = r10     // Catch:{ all -> 0x012d }
            r1.L$3 = r8     // Catch:{ all -> 0x012d }
            r1.L$4 = r2     // Catch:{ all -> 0x012d }
            r1.L$5 = r7     // Catch:{ all -> 0x012d }
            r1.L$6 = r6     // Catch:{ all -> 0x012d }
            r1.L$7 = r3     // Catch:{ all -> 0x012d }
            r1.label = r4     // Catch:{ all -> 0x012d }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x012d }
            if (r0 != r9) goto L_0x0106
            return r9
        L_0x0106:
            r14 = r8
            r8 = r1
            r1 = r14
            goto L_0x0052
        L_0x010b:
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r0)     // Catch:{ all -> 0x012d }
            if (r0 == 0) goto L_0x011b
            int r0 = r7.element     // Catch:{ all -> 0x012d }
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)     // Catch:{ all -> 0x012d }
            r2.cancel(r10)
            return r0
        L_0x011b:
            int r0 = r7.element     // Catch:{ all -> 0x012d }
            int r0 = r0 + r5
            r7.element = r0     // Catch:{ all -> 0x012d }
            r0 = r12
            goto L_0x00bb
        L_0x0122:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x012d }
            r2.cancel(r7)
            r0 = -1
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)
            return r0
        L_0x012d:
            r0 = move-exception
            goto L_0x0132
        L_0x012f:
            r0 = move-exception
            r2 = r18
        L_0x0132:
            r1 = r0
        L_0x0133:
            throw r1     // Catch:{ all -> 0x0134 }
        L_0x0134:
            r0 = move-exception
            r3 = r0
            r2.cancel(r1)
            throw r3
        L_0x013a:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.indexOf(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d8 A[Catch:{ all -> 0x0143 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f1 A[Catch:{ all -> 0x0143 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x012b A[SYNTHETIC, Splitter:B:51:0x012b] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object indexOfFirst(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r19, kotlin.coroutines.Continuation<? super java.lang.Integer> r20) {
        /*
            r0 = r20
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfFirst$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfFirst$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfFirst$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfFirst$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfFirst$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00a0
            if (r3 == r5) goto L_0x006c
            if (r3 != r4) goto L_0x0064
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009b }
            if (r13 != 0) goto L_0x005f
            r14 = r8
            r8 = r1
            r1 = r9
            r9 = r2
            r2 = r14
        L_0x0054:
            r15 = r11
            r11 = r3
            r3 = r6
            r6 = r15
            r16 = r10
            r10 = r7
            r7 = r16
            goto L_0x010f
        L_0x005f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009b }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009b }
            throw r0     // Catch:{ all -> 0x009b }
        L_0x0064:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006c:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009b }
            if (r13 != 0) goto L_0x0096
            r14 = r9
            r9 = r2
            r2 = r8
            r8 = r14
            goto L_0x00e9
        L_0x0096:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009b }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009b }
            throw r0     // Catch:{ all -> 0x009b }
        L_0x009b:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x0149
        L_0x00a0:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0156
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r6 = r18.iterator()     // Catch:{ all -> 0x0145 }
            r7 = r0
            r8 = r1
            r9 = r2
            r10 = r3
            r11 = r6
            r0 = r18
            r1 = r0
            r2 = r1
            r3 = r2
            r6 = r19
        L_0x00bf:
            r8.L$0 = r0     // Catch:{ all -> 0x0143 }
            r8.L$1 = r6     // Catch:{ all -> 0x0143 }
            r8.L$2 = r7     // Catch:{ all -> 0x0143 }
            r8.L$3 = r1     // Catch:{ all -> 0x0143 }
            r8.L$4 = r2     // Catch:{ all -> 0x0143 }
            r8.L$5 = r10     // Catch:{ all -> 0x0143 }
            r8.L$6 = r3     // Catch:{ all -> 0x0143 }
            r8.L$7 = r11     // Catch:{ all -> 0x0143 }
            r8.label = r5     // Catch:{ all -> 0x0143 }
            java.lang.Object r12 = r11.hasNext(r8)     // Catch:{ all -> 0x0143 }
            if (r12 != r9) goto L_0x00d8
            return r9
        L_0x00d8:
            r14 = r12
            r12 = r0
            r0 = r14
            r15 = r8
            r8 = r1
            r1 = r15
            r16 = r6
            r6 = r3
            r3 = r11
            r11 = r16
            r17 = r10
            r10 = r7
            r7 = r17
        L_0x00e9:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0143 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0143 }
            if (r0 == 0) goto L_0x0132
            r1.L$0 = r12     // Catch:{ all -> 0x0143 }
            r1.L$1 = r11     // Catch:{ all -> 0x0143 }
            r1.L$2 = r10     // Catch:{ all -> 0x0143 }
            r1.L$3 = r8     // Catch:{ all -> 0x0143 }
            r1.L$4 = r2     // Catch:{ all -> 0x0143 }
            r1.L$5 = r7     // Catch:{ all -> 0x0143 }
            r1.L$6 = r6     // Catch:{ all -> 0x0143 }
            r1.L$7 = r3     // Catch:{ all -> 0x0143 }
            r1.label = r4     // Catch:{ all -> 0x0143 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0143 }
            if (r0 != r9) goto L_0x010a
            return r9
        L_0x010a:
            r14 = r8
            r8 = r1
            r1 = r14
            goto L_0x0054
        L_0x010f:
            java.lang.Object r0 = r6.invoke(r0)     // Catch:{ all -> 0x0143 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0143 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0143 }
            if (r0 == 0) goto L_0x012b
            int r0 = r7.element     // Catch:{ all -> 0x0143 }
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)     // Catch:{ all -> 0x0143 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            r2.cancel(r10)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            return r0
        L_0x012b:
            int r0 = r7.element     // Catch:{ all -> 0x0143 }
            int r0 = r0 + r5
            r7.element = r0     // Catch:{ all -> 0x0143 }
            r0 = r12
            goto L_0x00bf
        L_0x0132:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0143 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            r0 = -1
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)
            return r0
        L_0x0143:
            r0 = move-exception
            goto L_0x0148
        L_0x0145:
            r0 = move-exception
            r2 = r18
        L_0x0148:
            r1 = r0
        L_0x0149:
            throw r1     // Catch:{ all -> 0x014a }
        L_0x014a:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x0156:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.indexOfFirst(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ec A[Catch:{ all -> 0x0151 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ed A[Catch:{ all -> 0x0151 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0108 A[Catch:{ all -> 0x0151 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0134 A[Catch:{ all -> 0x0151 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object indexOfLast(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r19, kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r20, kotlin.coroutines.Continuation<? super java.lang.Integer> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfLast$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfLast$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfLast$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfLast$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$indexOfLast$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00aa
            if (r3 == r5) goto L_0x0072
            if (r3 != r4) goto L_0x006a
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.internal.Ref$IntRef r11 = (kotlin.jvm.internal.Ref.IntRef) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a5 }
            if (r14 != 0) goto L_0x0065
            r15 = r8
            r8 = r1
            r1 = r9
            r9 = r2
            r2 = r15
        L_0x0058:
            r16 = r12
            r12 = r3
            r3 = r6
            r6 = r16
            r17 = r11
            r11 = r7
            r7 = r17
            goto L_0x0128
        L_0x0065:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a5 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a5 }
            throw r0     // Catch:{ all -> 0x00a5 }
        L_0x006a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0072:
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.internal.Ref$IntRef r11 = (kotlin.jvm.internal.Ref.IntRef) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a5 }
            if (r14 != 0) goto L_0x00a0
            r15 = r9
            r9 = r2
            r2 = r8
            r8 = r15
            goto L_0x0100
        L_0x00a0:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a5 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a5 }
            throw r0     // Catch:{ all -> 0x00a5 }
        L_0x00a5:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x0157
        L_0x00aa:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0164
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = -1
            r0.element = r3
            kotlin.jvm.internal.Ref$IntRef r3 = new kotlin.jvm.internal.Ref$IntRef
            r3.<init>()
            r6 = 0
            r3.element = r6
            r6 = 0
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r7 = r19.iterator()     // Catch:{ all -> 0x0153 }
            r8 = r1
            r9 = r2
            r10 = r3
            r11 = r6
            r12 = r7
            r1 = r19
            r2 = r1
            r3 = r2
            r6 = r20
            r7 = r0
            r0 = r3
        L_0x00d2:
            r8.L$0 = r0     // Catch:{ all -> 0x0151 }
            r8.L$1 = r6     // Catch:{ all -> 0x0151 }
            r8.L$2 = r7     // Catch:{ all -> 0x0151 }
            r8.L$3 = r10     // Catch:{ all -> 0x0151 }
            r8.L$4 = r1     // Catch:{ all -> 0x0151 }
            r8.L$5 = r2     // Catch:{ all -> 0x0151 }
            r8.L$6 = r11     // Catch:{ all -> 0x0151 }
            r8.L$7 = r3     // Catch:{ all -> 0x0151 }
            r8.L$8 = r12     // Catch:{ all -> 0x0151 }
            r8.label = r5     // Catch:{ all -> 0x0151 }
            java.lang.Object r13 = r12.hasNext(r8)     // Catch:{ all -> 0x0151 }
            if (r13 != r9) goto L_0x00ed
            return r9
        L_0x00ed:
            r15 = r13
            r13 = r0
            r0 = r15
            r16 = r8
            r8 = r1
            r1 = r16
            r17 = r6
            r6 = r3
            r3 = r12
            r12 = r17
            r18 = r11
            r11 = r7
            r7 = r18
        L_0x0100:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0151 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0151 }
            if (r0 == 0) goto L_0x013f
            r1.L$0 = r13     // Catch:{ all -> 0x0151 }
            r1.L$1 = r12     // Catch:{ all -> 0x0151 }
            r1.L$2 = r11     // Catch:{ all -> 0x0151 }
            r1.L$3 = r10     // Catch:{ all -> 0x0151 }
            r1.L$4 = r8     // Catch:{ all -> 0x0151 }
            r1.L$5 = r2     // Catch:{ all -> 0x0151 }
            r1.L$6 = r7     // Catch:{ all -> 0x0151 }
            r1.L$7 = r6     // Catch:{ all -> 0x0151 }
            r1.L$8 = r3     // Catch:{ all -> 0x0151 }
            r1.label = r4     // Catch:{ all -> 0x0151 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0151 }
            if (r0 != r9) goto L_0x0123
            return r9
        L_0x0123:
            r15 = r8
            r8 = r1
            r1 = r15
            goto L_0x0058
        L_0x0128:
            java.lang.Object r0 = r6.invoke(r0)     // Catch:{ all -> 0x0151 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0151 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0151 }
            if (r0 == 0) goto L_0x0138
            int r0 = r10.element     // Catch:{ all -> 0x0151 }
            r7.element = r0     // Catch:{ all -> 0x0151 }
        L_0x0138:
            int r0 = r10.element     // Catch:{ all -> 0x0151 }
            int r0 = r0 + r5
            r10.element = r0     // Catch:{ all -> 0x0151 }
            r0 = r13
            goto L_0x00d2
        L_0x013f:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0151 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            int r0 = r11.element
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)
            return r0
        L_0x0151:
            r0 = move-exception
            goto L_0x0156
        L_0x0153:
            r0 = move-exception
            r2 = r19
        L_0x0156:
            r1 = r0
        L_0x0157:
            throw r1     // Catch:{ all -> 0x0158 }
        L_0x0158:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x0164:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.indexOfLast(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ed A[Catch:{ all -> 0x00bc }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0117 A[Catch:{ all -> 0x009b }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0118 A[Catch:{ all -> 0x009b }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0127 A[Catch:{ all -> 0x009b }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0144 A[SYNTHETIC, Splitter:B:74:0x0144] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object last(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, kotlin.coroutines.Continuation<? super E> r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$1
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L_0x00c0
            if (r2 == r6) goto L_0x009e
            if (r2 == r5) goto L_0x007c
            if (r2 == r4) goto L_0x0058
            if (r2 != r3) goto L_0x0050
            java.lang.Object r11 = r0.L$5
            java.lang.Object r11 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$2
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            boolean r8 = r12 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009b }
            if (r8 != 0) goto L_0x004b
            goto L_0x0103
        L_0x004b:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12     // Catch:{ all -> 0x009b }
            java.lang.Throwable r11 = r12.exception     // Catch:{ all -> 0x009b }
            throw r11     // Catch:{ all -> 0x009b }
        L_0x0050:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0058:
            java.lang.Object r11 = r0.L$5
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r12 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00bc }
            if (r9 != 0) goto L_0x0077
            r10 = r7
            r7 = r6
            r6 = r10
            goto L_0x011f
        L_0x0077:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12     // Catch:{ all -> 0x00bc }
            java.lang.Throwable r11 = r12.exception     // Catch:{ all -> 0x00bc }
            throw r11     // Catch:{ all -> 0x00bc }
        L_0x007c:
            java.lang.Object r11 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$2
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            boolean r8 = r12 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009b }
            if (r8 != 0) goto L_0x0096
            goto L_0x0103
        L_0x0096:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12     // Catch:{ all -> 0x009b }
            java.lang.Throwable r11 = r12.exception     // Catch:{ all -> 0x009b }
            throw r11     // Catch:{ all -> 0x009b }
        L_0x009b:
            r11 = move-exception
            goto L_0x0151
        L_0x009e:
            java.lang.Object r11 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r6 = r0.L$2
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r12 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00bc }
            if (r9 != 0) goto L_0x00b7
            goto L_0x00e5
        L_0x00b7:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12     // Catch:{ all -> 0x00bc }
            java.lang.Throwable r11 = r12.exception     // Catch:{ all -> 0x00bc }
            throw r11     // Catch:{ all -> 0x00bc }
        L_0x00bc:
            r11 = move-exception
            r6 = r7
            goto L_0x0151
        L_0x00c0:
            boolean r2 = r12 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x0157
            r12 = 0
            java.lang.Throwable r12 = (java.lang.Throwable) r12
            kotlinx.coroutines.channels.ChannelIterator r2 = r11.iterator()     // Catch:{ all -> 0x014e }
            r0.L$0 = r11     // Catch:{ all -> 0x014e }
            r0.L$1 = r11     // Catch:{ all -> 0x014e }
            r0.L$2 = r12     // Catch:{ all -> 0x014e }
            r0.L$3 = r11     // Catch:{ all -> 0x014e }
            r0.L$4 = r2     // Catch:{ all -> 0x014e }
            r0.label = r6     // Catch:{ all -> 0x014e }
            java.lang.Object r6 = r2.hasNext(r0)     // Catch:{ all -> 0x014e }
            if (r6 != r1) goto L_0x00de
            return r1
        L_0x00de:
            r7 = r11
            r8 = r7
            r11 = r2
            r2 = r8
            r10 = r6
            r6 = r12
            r12 = r10
        L_0x00e5:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ all -> 0x00bc }
            boolean r12 = r12.booleanValue()     // Catch:{ all -> 0x00bc }
            if (r12 == 0) goto L_0x0144
            r0.L$0 = r8     // Catch:{ all -> 0x00bc }
            r0.L$1 = r7     // Catch:{ all -> 0x00bc }
            r0.L$2 = r6     // Catch:{ all -> 0x00bc }
            r0.L$3 = r2     // Catch:{ all -> 0x00bc }
            r0.L$4 = r11     // Catch:{ all -> 0x00bc }
            r0.label = r5     // Catch:{ all -> 0x00bc }
            java.lang.Object r12 = r11.next(r0)     // Catch:{ all -> 0x00bc }
            if (r12 != r1) goto L_0x0100
            return r1
        L_0x0100:
            r5 = r6
            r6 = r7
        L_0x0102:
            r7 = r8
        L_0x0103:
            r0.L$0 = r7     // Catch:{ all -> 0x009b }
            r0.L$1 = r6     // Catch:{ all -> 0x009b }
            r0.L$2 = r5     // Catch:{ all -> 0x009b }
            r0.L$3 = r2     // Catch:{ all -> 0x009b }
            r0.L$4 = r11     // Catch:{ all -> 0x009b }
            r0.L$5 = r12     // Catch:{ all -> 0x009b }
            r0.label = r4     // Catch:{ all -> 0x009b }
            java.lang.Object r8 = r11.hasNext(r0)     // Catch:{ all -> 0x009b }
            if (r8 != r1) goto L_0x0118
            return r1
        L_0x0118:
            r10 = r2
            r2 = r11
            r11 = r12
            r12 = r8
            r8 = r7
            r7 = r5
            r5 = r10
        L_0x011f:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ all -> 0x009b }
            boolean r12 = r12.booleanValue()     // Catch:{ all -> 0x009b }
            if (r12 == 0) goto L_0x0140
            r0.L$0 = r8     // Catch:{ all -> 0x009b }
            r0.L$1 = r6     // Catch:{ all -> 0x009b }
            r0.L$2 = r7     // Catch:{ all -> 0x009b }
            r0.L$3 = r5     // Catch:{ all -> 0x009b }
            r0.L$4 = r2     // Catch:{ all -> 0x009b }
            r0.L$5 = r11     // Catch:{ all -> 0x009b }
            r0.label = r3     // Catch:{ all -> 0x009b }
            java.lang.Object r12 = r2.next(r0)     // Catch:{ all -> 0x009b }
            if (r12 != r1) goto L_0x013c
            return r1
        L_0x013c:
            r11 = r2
            r2 = r5
            r5 = r7
            goto L_0x0102
        L_0x0140:
            r6.cancel(r7)
            return r11
        L_0x0144:
            java.util.NoSuchElementException r11 = new java.util.NoSuchElementException     // Catch:{ all -> 0x00bc }
            java.lang.String r12 = "ReceiveChannel is empty."
            r11.<init>(r12)     // Catch:{ all -> 0x00bc }
            java.lang.Throwable r11 = (java.lang.Throwable) r11     // Catch:{ all -> 0x00bc }
            throw r11     // Catch:{ all -> 0x00bc }
        L_0x014e:
            r12 = move-exception
            r6 = r11
            r11 = r12
        L_0x0151:
            throw r11     // Catch:{ all -> 0x0152 }
        L_0x0152:
            r12 = move-exception
            r6.cancel(r11)
            throw r12
        L_0x0157:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
            java.lang.Throwable r11 = r12.exception
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.last(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ec A[Catch:{ all -> 0x0157 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ed A[Catch:{ all -> 0x0157 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0109 A[Catch:{ all -> 0x0157 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0135 A[Catch:{ all -> 0x0157 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x014a  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x014d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object last(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r19, kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r20, kotlin.coroutines.Continuation<? super E> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$3 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$3 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$last$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00ab
            if (r3 == r5) goto L_0x0073
            if (r3 != r4) goto L_0x006b
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$BooleanRef r10 = (kotlin.jvm.internal.Ref.BooleanRef) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r11 = (kotlin.jvm.internal.Ref.ObjectRef) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a6 }
            if (r14 != 0) goto L_0x0066
            r15 = r8
            r8 = r1
            r1 = r9
            r9 = r2
            r2 = r15
        L_0x0058:
            r16 = r12
            r12 = r3
            r3 = r6
            r6 = r16
            r17 = r10
            r10 = r7
            r7 = r11
            r11 = r17
            goto L_0x0129
        L_0x0066:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a6 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a6 }
            throw r0     // Catch:{ all -> 0x00a6 }
        L_0x006b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0073:
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$BooleanRef r10 = (kotlin.jvm.internal.Ref.BooleanRef) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r11 = (kotlin.jvm.internal.Ref.ObjectRef) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a6 }
            if (r14 != 0) goto L_0x00a1
            r15 = r9
            r9 = r2
            r2 = r8
            r8 = r15
            goto L_0x0101
        L_0x00a1:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a6 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a6 }
            throw r0     // Catch:{ all -> 0x00a6 }
        L_0x00a6:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x015d
        L_0x00ab:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x016a
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            kotlin.jvm.internal.Ref$BooleanRef r6 = new kotlin.jvm.internal.Ref$BooleanRef
            r6.<init>()
            r7 = 0
            r6.element = r7
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r7 = r19.iterator()     // Catch:{ all -> 0x0159 }
            r8 = r1
            r9 = r2
            r10 = r3
            r11 = r6
            r12 = r7
            r1 = r19
            r2 = r1
            r3 = r2
            r6 = r20
            r7 = r0
            r0 = r3
        L_0x00d2:
            r8.L$0 = r0     // Catch:{ all -> 0x0157 }
            r8.L$1 = r6     // Catch:{ all -> 0x0157 }
            r8.L$2 = r7     // Catch:{ all -> 0x0157 }
            r8.L$3 = r11     // Catch:{ all -> 0x0157 }
            r8.L$4 = r1     // Catch:{ all -> 0x0157 }
            r8.L$5 = r2     // Catch:{ all -> 0x0157 }
            r8.L$6 = r10     // Catch:{ all -> 0x0157 }
            r8.L$7 = r3     // Catch:{ all -> 0x0157 }
            r8.L$8 = r12     // Catch:{ all -> 0x0157 }
            r8.label = r5     // Catch:{ all -> 0x0157 }
            java.lang.Object r13 = r12.hasNext(r8)     // Catch:{ all -> 0x0157 }
            if (r13 != r9) goto L_0x00ed
            return r9
        L_0x00ed:
            r15 = r13
            r13 = r0
            r0 = r15
            r16 = r8
            r8 = r1
            r1 = r16
            r17 = r6
            r6 = r3
            r3 = r12
            r12 = r17
            r18 = r11
            r11 = r7
            r7 = r10
            r10 = r18
        L_0x0101:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0157 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0157 }
            if (r0 == 0) goto L_0x013b
            r1.L$0 = r13     // Catch:{ all -> 0x0157 }
            r1.L$1 = r12     // Catch:{ all -> 0x0157 }
            r1.L$2 = r11     // Catch:{ all -> 0x0157 }
            r1.L$3 = r10     // Catch:{ all -> 0x0157 }
            r1.L$4 = r8     // Catch:{ all -> 0x0157 }
            r1.L$5 = r2     // Catch:{ all -> 0x0157 }
            r1.L$6 = r7     // Catch:{ all -> 0x0157 }
            r1.L$7 = r6     // Catch:{ all -> 0x0157 }
            r1.L$8 = r3     // Catch:{ all -> 0x0157 }
            r1.label = r4     // Catch:{ all -> 0x0157 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0157 }
            if (r0 != r9) goto L_0x0124
            return r9
        L_0x0124:
            r15 = r8
            r8 = r1
            r1 = r15
            goto L_0x0058
        L_0x0129:
            java.lang.Object r14 = r6.invoke(r0)     // Catch:{ all -> 0x0157 }
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ all -> 0x0157 }
            boolean r14 = r14.booleanValue()     // Catch:{ all -> 0x0157 }
            if (r14 == 0) goto L_0x0139
            r7.element = r0     // Catch:{ all -> 0x0157 }
            r11.element = r5     // Catch:{ all -> 0x0157 }
        L_0x0139:
            r0 = r13
            goto L_0x00d2
        L_0x013b:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0157 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            boolean r0 = r10.element
            if (r0 == 0) goto L_0x014d
            T r0 = r11.element
            return r0
        L_0x014d:
            java.util.NoSuchElementException r0 = new java.util.NoSuchElementException
            java.lang.String r1 = "ReceiveChannel contains no element matching the predicate."
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x0157:
            r0 = move-exception
            goto L_0x015c
        L_0x0159:
            r0 = move-exception
            r2 = r19
        L_0x015c:
            r1 = r0
        L_0x015d:
            throw r1     // Catch:{ all -> 0x015e }
        L_0x015e:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x016a:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.last(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e8 A[Catch:{ all -> 0x0141 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e9 A[Catch:{ all -> 0x0141 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0104 A[Catch:{ all -> 0x0141 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x012a A[Catch:{ all -> 0x0141 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object lastIndexOf(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r19, E r20, kotlin.coroutines.Continuation<? super java.lang.Integer> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastIndexOf$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastIndexOf$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastIndexOf$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastIndexOf$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastIndexOf$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00a6
            if (r3 == r5) goto L_0x0070
            if (r3 != r4) goto L_0x0068
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.internal.Ref$IntRef r11 = (kotlin.jvm.internal.Ref.IntRef) r11
            java.lang.Object r12 = r1.L$1
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a1 }
            if (r14 != 0) goto L_0x0063
            r15 = r8
            r8 = r1
            r1 = r9
            r9 = r2
            r2 = r15
        L_0x0056:
            r16 = r12
            r12 = r3
            r3 = r6
            r6 = r16
            r17 = r11
            r11 = r7
            r7 = r17
            goto L_0x0124
        L_0x0063:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a1 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a1 }
            throw r0     // Catch:{ all -> 0x00a1 }
        L_0x0068:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0070:
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.internal.Ref$IntRef r11 = (kotlin.jvm.internal.Ref.IntRef) r11
            java.lang.Object r12 = r1.L$1
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a1 }
            if (r14 != 0) goto L_0x009c
            r15 = r9
            r9 = r2
            r2 = r8
            r8 = r15
            goto L_0x00fc
        L_0x009c:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a1 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a1 }
            throw r0     // Catch:{ all -> 0x00a1 }
        L_0x00a1:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x0147
        L_0x00a6:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x014e
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = -1
            r0.element = r3
            kotlin.jvm.internal.Ref$IntRef r3 = new kotlin.jvm.internal.Ref$IntRef
            r3.<init>()
            r6 = 0
            r3.element = r6
            r6 = 0
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r7 = r19.iterator()     // Catch:{ all -> 0x0143 }
            r8 = r1
            r9 = r2
            r10 = r3
            r11 = r6
            r12 = r7
            r1 = r19
            r2 = r1
            r3 = r2
            r6 = r20
            r7 = r0
            r0 = r3
        L_0x00ce:
            r8.L$0 = r0     // Catch:{ all -> 0x0141 }
            r8.L$1 = r6     // Catch:{ all -> 0x0141 }
            r8.L$2 = r7     // Catch:{ all -> 0x0141 }
            r8.L$3 = r10     // Catch:{ all -> 0x0141 }
            r8.L$4 = r1     // Catch:{ all -> 0x0141 }
            r8.L$5 = r2     // Catch:{ all -> 0x0141 }
            r8.L$6 = r11     // Catch:{ all -> 0x0141 }
            r8.L$7 = r3     // Catch:{ all -> 0x0141 }
            r8.L$8 = r12     // Catch:{ all -> 0x0141 }
            r8.label = r5     // Catch:{ all -> 0x0141 }
            java.lang.Object r13 = r12.hasNext(r8)     // Catch:{ all -> 0x0141 }
            if (r13 != r9) goto L_0x00e9
            return r9
        L_0x00e9:
            r15 = r13
            r13 = r0
            r0 = r15
            r16 = r8
            r8 = r1
            r1 = r16
            r17 = r6
            r6 = r3
            r3 = r12
            r12 = r17
            r18 = r11
            r11 = r7
            r7 = r18
        L_0x00fc:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0141 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0141 }
            if (r0 == 0) goto L_0x0135
            r1.L$0 = r13     // Catch:{ all -> 0x0141 }
            r1.L$1 = r12     // Catch:{ all -> 0x0141 }
            r1.L$2 = r11     // Catch:{ all -> 0x0141 }
            r1.L$3 = r10     // Catch:{ all -> 0x0141 }
            r1.L$4 = r8     // Catch:{ all -> 0x0141 }
            r1.L$5 = r2     // Catch:{ all -> 0x0141 }
            r1.L$6 = r7     // Catch:{ all -> 0x0141 }
            r1.L$7 = r6     // Catch:{ all -> 0x0141 }
            r1.L$8 = r3     // Catch:{ all -> 0x0141 }
            r1.label = r4     // Catch:{ all -> 0x0141 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0141 }
            if (r0 != r9) goto L_0x011f
            return r9
        L_0x011f:
            r15 = r8
            r8 = r1
            r1 = r15
            goto L_0x0056
        L_0x0124:
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r0)     // Catch:{ all -> 0x0141 }
            if (r0 == 0) goto L_0x012e
            int r0 = r10.element     // Catch:{ all -> 0x0141 }
            r7.element = r0     // Catch:{ all -> 0x0141 }
        L_0x012e:
            int r0 = r10.element     // Catch:{ all -> 0x0141 }
            int r0 = r0 + r5
            r10.element = r0     // Catch:{ all -> 0x0141 }
            r0 = r13
            goto L_0x00ce
        L_0x0135:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0141 }
            r2.cancel(r7)
            int r0 = r11.element
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)
            return r0
        L_0x0141:
            r0 = move-exception
            goto L_0x0146
        L_0x0143:
            r0 = move-exception
            r2 = r19
        L_0x0146:
            r1 = r0
        L_0x0147:
            throw r1     // Catch:{ all -> 0x0148 }
        L_0x0148:
            r0 = move-exception
            r3 = r0
            r2.cancel(r1)
            throw r3
        L_0x014e:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.lastIndexOf(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00f6 A[SYNTHETIC, Splitter:B:60:0x00f6] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0120 A[Catch:{ all -> 0x00a0 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0121 A[Catch:{ all -> 0x00a0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0130 A[Catch:{ all -> 0x00a0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object lastOrNull(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, kotlin.coroutines.Continuation<? super E> r13) {
        /*
            boolean r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$1
            r0.<init>(r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            if (r2 == 0) goto L_0x00c5
            if (r2 == r7) goto L_0x00a3
            if (r2 == r6) goto L_0x0081
            if (r2 == r5) goto L_0x0059
            if (r2 != r4) goto L_0x0051
            java.lang.Object r12 = r0.L$5
            java.lang.Object r12 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r3 = r0.L$2
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            boolean r8 = r13 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a0 }
            if (r8 != 0) goto L_0x004c
            goto L_0x010c
        L_0x004c:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13     // Catch:{ all -> 0x00a0 }
            java.lang.Throwable r12 = r13.exception     // Catch:{ all -> 0x00a0 }
            throw r12     // Catch:{ all -> 0x00a0 }
        L_0x0051:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0059:
            java.lang.Object r12 = r0.L$5
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r3 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3
            java.lang.Object r6 = r0.L$2
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r13 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x007d }
            if (r9 != 0) goto L_0x0078
            r11 = r7
            r7 = r6
            r6 = r11
            goto L_0x0128
        L_0x0078:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13     // Catch:{ all -> 0x007d }
            java.lang.Throwable r12 = r13.exception     // Catch:{ all -> 0x007d }
            throw r12     // Catch:{ all -> 0x007d }
        L_0x007d:
            r12 = move-exception
            r6 = r7
            goto L_0x0151
        L_0x0081:
            java.lang.Object r12 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r3 = r0.L$2
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            boolean r8 = r13 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a0 }
            if (r8 != 0) goto L_0x009b
            goto L_0x010c
        L_0x009b:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13     // Catch:{ all -> 0x00a0 }
            java.lang.Throwable r12 = r13.exception     // Catch:{ all -> 0x00a0 }
            throw r12     // Catch:{ all -> 0x00a0 }
        L_0x00a0:
            r12 = move-exception
            goto L_0x0151
        L_0x00a3:
            java.lang.Object r12 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r7 = r0.L$2
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            boolean r10 = r13 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00c1 }
            if (r10 != 0) goto L_0x00bc
            goto L_0x00ea
        L_0x00bc:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13     // Catch:{ all -> 0x00c1 }
            java.lang.Throwable r12 = r13.exception     // Catch:{ all -> 0x00c1 }
            throw r12     // Catch:{ all -> 0x00c1 }
        L_0x00c1:
            r12 = move-exception
            r6 = r8
            goto L_0x0151
        L_0x00c5:
            boolean r2 = r13 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x0157
            r13 = r3
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            kotlinx.coroutines.channels.ChannelIterator r2 = r12.iterator()     // Catch:{ all -> 0x014e }
            r0.L$0 = r12     // Catch:{ all -> 0x014e }
            r0.L$1 = r12     // Catch:{ all -> 0x014e }
            r0.L$2 = r13     // Catch:{ all -> 0x014e }
            r0.L$3 = r12     // Catch:{ all -> 0x014e }
            r0.L$4 = r2     // Catch:{ all -> 0x014e }
            r0.label = r7     // Catch:{ all -> 0x014e }
            java.lang.Object r7 = r2.hasNext(r0)     // Catch:{ all -> 0x014e }
            if (r7 != r1) goto L_0x00e3
            return r1
        L_0x00e3:
            r8 = r12
            r9 = r8
            r12 = r2
            r2 = r9
            r11 = r7
            r7 = r13
            r13 = r11
        L_0x00ea:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ all -> 0x00c1 }
            boolean r13 = r13.booleanValue()     // Catch:{ all -> 0x00c1 }
            if (r13 != 0) goto L_0x00f6
            r8.cancel(r7)
            return r3
        L_0x00f6:
            r0.L$0 = r9     // Catch:{ all -> 0x00c1 }
            r0.L$1 = r8     // Catch:{ all -> 0x00c1 }
            r0.L$2 = r7     // Catch:{ all -> 0x00c1 }
            r0.L$3 = r2     // Catch:{ all -> 0x00c1 }
            r0.L$4 = r12     // Catch:{ all -> 0x00c1 }
            r0.label = r6     // Catch:{ all -> 0x00c1 }
            java.lang.Object r13 = r12.next(r0)     // Catch:{ all -> 0x00c1 }
            if (r13 != r1) goto L_0x0109
            return r1
        L_0x0109:
            r3 = r7
            r6 = r8
            r7 = r9
        L_0x010c:
            r0.L$0 = r7     // Catch:{ all -> 0x00a0 }
            r0.L$1 = r6     // Catch:{ all -> 0x00a0 }
            r0.L$2 = r3     // Catch:{ all -> 0x00a0 }
            r0.L$3 = r2     // Catch:{ all -> 0x00a0 }
            r0.L$4 = r12     // Catch:{ all -> 0x00a0 }
            r0.L$5 = r13     // Catch:{ all -> 0x00a0 }
            r0.label = r5     // Catch:{ all -> 0x00a0 }
            java.lang.Object r8 = r12.hasNext(r0)     // Catch:{ all -> 0x00a0 }
            if (r8 != r1) goto L_0x0121
            return r1
        L_0x0121:
            r11 = r2
            r2 = r12
            r12 = r13
            r13 = r8
            r8 = r7
            r7 = r3
            r3 = r11
        L_0x0128:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ all -> 0x00a0 }
            boolean r13 = r13.booleanValue()     // Catch:{ all -> 0x00a0 }
            if (r13 == 0) goto L_0x014a
            r0.L$0 = r8     // Catch:{ all -> 0x00a0 }
            r0.L$1 = r6     // Catch:{ all -> 0x00a0 }
            r0.L$2 = r7     // Catch:{ all -> 0x00a0 }
            r0.L$3 = r3     // Catch:{ all -> 0x00a0 }
            r0.L$4 = r2     // Catch:{ all -> 0x00a0 }
            r0.L$5 = r12     // Catch:{ all -> 0x00a0 }
            r0.label = r4     // Catch:{ all -> 0x00a0 }
            java.lang.Object r13 = r2.next(r0)     // Catch:{ all -> 0x00a0 }
            if (r13 != r1) goto L_0x0145
            return r1
        L_0x0145:
            r12 = r2
            r2 = r3
            r3 = r7
            r7 = r8
            goto L_0x010c
        L_0x014a:
            r6.cancel(r7)
            return r12
        L_0x014e:
            r13 = move-exception
            r6 = r12
            r12 = r13
        L_0x0151:
            throw r12     // Catch:{ all -> 0x0152 }
        L_0x0152:
            r13 = move-exception
            r6.cancel(r12)
            throw r13
        L_0x0157:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r12 = r13.exception
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.lastOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d6 A[Catch:{ all -> 0x012c }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d7 A[Catch:{ all -> 0x012c }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f0 A[Catch:{ all -> 0x012c }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x011a A[Catch:{ all -> 0x012c }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object lastOrNull(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r19, kotlin.coroutines.Continuation<? super E> r20) {
        /*
            r0 = r20
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$3 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$3 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$lastOrNull$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00a0
            if (r3 == r5) goto L_0x006c
            if (r3 != r4) goto L_0x0064
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009b }
            if (r13 != 0) goto L_0x005f
            r14 = r8
            r8 = r1
            r1 = r9
            r9 = r2
            r2 = r14
        L_0x0054:
            r15 = r11
            r11 = r3
            r3 = r6
            r6 = r15
            r16 = r10
            r10 = r7
            r7 = r16
            goto L_0x010e
        L_0x005f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009b }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009b }
            throw r0     // Catch:{ all -> 0x009b }
        L_0x0064:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006c:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009b }
            if (r13 != 0) goto L_0x0096
            r14 = r9
            r9 = r2
            r2 = r8
            r8 = r14
            goto L_0x00e8
        L_0x0096:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009b }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009b }
            throw r0     // Catch:{ all -> 0x009b }
        L_0x009b:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x0132
        L_0x00a0:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x013f
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r6 = r18.iterator()     // Catch:{ all -> 0x012e }
            r7 = r0
            r8 = r1
            r9 = r2
            r10 = r3
            r11 = r6
            r0 = r18
            r1 = r0
            r2 = r1
            r3 = r2
            r6 = r19
        L_0x00be:
            r8.L$0 = r0     // Catch:{ all -> 0x012c }
            r8.L$1 = r6     // Catch:{ all -> 0x012c }
            r8.L$2 = r7     // Catch:{ all -> 0x012c }
            r8.L$3 = r1     // Catch:{ all -> 0x012c }
            r8.L$4 = r2     // Catch:{ all -> 0x012c }
            r8.L$5 = r10     // Catch:{ all -> 0x012c }
            r8.L$6 = r3     // Catch:{ all -> 0x012c }
            r8.L$7 = r11     // Catch:{ all -> 0x012c }
            r8.label = r5     // Catch:{ all -> 0x012c }
            java.lang.Object r12 = r11.hasNext(r8)     // Catch:{ all -> 0x012c }
            if (r12 != r9) goto L_0x00d7
            return r9
        L_0x00d7:
            r14 = r12
            r12 = r0
            r0 = r14
            r15 = r8
            r8 = r1
            r1 = r15
            r16 = r6
            r6 = r3
            r3 = r11
            r11 = r16
            r17 = r10
            r10 = r7
            r7 = r17
        L_0x00e8:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x012c }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x012c }
            if (r0 == 0) goto L_0x011e
            r1.L$0 = r12     // Catch:{ all -> 0x012c }
            r1.L$1 = r11     // Catch:{ all -> 0x012c }
            r1.L$2 = r10     // Catch:{ all -> 0x012c }
            r1.L$3 = r8     // Catch:{ all -> 0x012c }
            r1.L$4 = r2     // Catch:{ all -> 0x012c }
            r1.L$5 = r7     // Catch:{ all -> 0x012c }
            r1.L$6 = r6     // Catch:{ all -> 0x012c }
            r1.L$7 = r3     // Catch:{ all -> 0x012c }
            r1.label = r4     // Catch:{ all -> 0x012c }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x012c }
            if (r0 != r9) goto L_0x0109
            return r9
        L_0x0109:
            r14 = r8
            r8 = r1
            r1 = r14
            goto L_0x0054
        L_0x010e:
            java.lang.Object r13 = r6.invoke(r0)     // Catch:{ all -> 0x012c }
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ all -> 0x012c }
            boolean r13 = r13.booleanValue()     // Catch:{ all -> 0x012c }
            if (r13 == 0) goto L_0x011c
            r7.element = r0     // Catch:{ all -> 0x012c }
        L_0x011c:
            r0 = r12
            goto L_0x00be
        L_0x011e:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x012c }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            T r0 = r10.element
            return r0
        L_0x012c:
            r0 = move-exception
            goto L_0x0131
        L_0x012e:
            r0 = move-exception
            r2 = r18
        L_0x0131:
            r1 = r0
        L_0x0132:
            throw r1     // Catch:{ all -> 0x0133 }
        L_0x0133:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x013f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.lastOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ca A[Catch:{ all -> 0x009a }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00f4 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0106 A[SYNTHETIC, Splitter:B:64:0x0106] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0110 A[SYNTHETIC, Splitter:B:67:0x0110] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object single(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r10, kotlin.coroutines.Continuation<? super E> r11) {
        /*
            boolean r0 = r11 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$1
            r0.<init>(r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x009d
            if (r2 == r5) goto L_0x007c
            if (r2 == r4) goto L_0x0059
            if (r2 != r3) goto L_0x0051
            java.lang.Object r10 = r0.L$5
            java.lang.Object r1 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r1 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1
            java.lang.Object r1 = r0.L$2
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            boolean r0 = r11 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x004d }
            if (r0 != 0) goto L_0x0048
            goto L_0x00fa
        L_0x0048:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11     // Catch:{ all -> 0x004d }
            java.lang.Throwable r10 = r11.exception     // Catch:{ all -> 0x004d }
            throw r10     // Catch:{ all -> 0x004d }
        L_0x004d:
            r10 = move-exception
            r6 = r2
            goto L_0x011d
        L_0x0051:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0059:
            java.lang.Object r10 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$2
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            java.lang.Object r5 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            boolean r7 = r11 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0078 }
            if (r7 != 0) goto L_0x0073
            goto L_0x00e0
        L_0x0073:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11     // Catch:{ all -> 0x0078 }
            java.lang.Throwable r10 = r11.exception     // Catch:{ all -> 0x0078 }
            throw r10     // Catch:{ all -> 0x0078 }
        L_0x0078:
            r10 = move-exception
            r6 = r5
            goto L_0x011d
        L_0x007c:
            java.lang.Object r10 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r10 = (kotlinx.coroutines.channels.ChannelIterator) r10
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$2
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            boolean r8 = r11 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009a }
            if (r8 != 0) goto L_0x0095
            goto L_0x00c2
        L_0x0095:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11     // Catch:{ all -> 0x009a }
            java.lang.Throwable r10 = r11.exception     // Catch:{ all -> 0x009a }
            throw r10     // Catch:{ all -> 0x009a }
        L_0x009a:
            r10 = move-exception
            goto L_0x011d
        L_0x009d:
            boolean r2 = r11 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x0123
            r11 = 0
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            kotlinx.coroutines.channels.ChannelIterator r2 = r10.iterator()     // Catch:{ all -> 0x011a }
            r0.L$0 = r10     // Catch:{ all -> 0x011a }
            r0.L$1 = r10     // Catch:{ all -> 0x011a }
            r0.L$2 = r11     // Catch:{ all -> 0x011a }
            r0.L$3 = r10     // Catch:{ all -> 0x011a }
            r0.L$4 = r2     // Catch:{ all -> 0x011a }
            r0.label = r5     // Catch:{ all -> 0x011a }
            java.lang.Object r5 = r2.hasNext(r0)     // Catch:{ all -> 0x011a }
            if (r5 != r1) goto L_0x00bb
            return r1
        L_0x00bb:
            r6 = r10
            r7 = r6
            r10 = r2
            r2 = r7
            r9 = r5
            r5 = r11
            r11 = r9
        L_0x00c2:
            java.lang.Boolean r11 = (java.lang.Boolean) r11     // Catch:{ all -> 0x009a }
            boolean r11 = r11.booleanValue()     // Catch:{ all -> 0x009a }
            if (r11 == 0) goto L_0x0110
            r0.L$0 = r7     // Catch:{ all -> 0x009a }
            r0.L$1 = r6     // Catch:{ all -> 0x009a }
            r0.L$2 = r5     // Catch:{ all -> 0x009a }
            r0.L$3 = r2     // Catch:{ all -> 0x009a }
            r0.L$4 = r10     // Catch:{ all -> 0x009a }
            r0.label = r4     // Catch:{ all -> 0x009a }
            java.lang.Object r11 = r10.next(r0)     // Catch:{ all -> 0x009a }
            if (r11 != r1) goto L_0x00dd
            return r1
        L_0x00dd:
            r4 = r5
            r5 = r6
            r6 = r7
        L_0x00e0:
            r0.L$0 = r6     // Catch:{ all -> 0x0078 }
            r0.L$1 = r5     // Catch:{ all -> 0x0078 }
            r0.L$2 = r4     // Catch:{ all -> 0x0078 }
            r0.L$3 = r2     // Catch:{ all -> 0x0078 }
            r0.L$4 = r10     // Catch:{ all -> 0x0078 }
            r0.L$5 = r11     // Catch:{ all -> 0x0078 }
            r0.label = r3     // Catch:{ all -> 0x0078 }
            java.lang.Object r10 = r10.hasNext(r0)     // Catch:{ all -> 0x0078 }
            if (r10 != r1) goto L_0x00f5
            return r1
        L_0x00f5:
            r1 = r4
            r2 = r5
            r9 = r11
            r11 = r10
            r10 = r9
        L_0x00fa:
            java.lang.Boolean r11 = (java.lang.Boolean) r11     // Catch:{ all -> 0x004d }
            boolean r11 = r11.booleanValue()     // Catch:{ all -> 0x004d }
            if (r11 != 0) goto L_0x0106
            r2.cancel(r1)
            return r10
        L_0x0106:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x004d }
            java.lang.String r11 = "ReceiveChannel has more than one element."
            r10.<init>(r11)     // Catch:{ all -> 0x004d }
            java.lang.Throwable r10 = (java.lang.Throwable) r10     // Catch:{ all -> 0x004d }
            throw r10     // Catch:{ all -> 0x004d }
        L_0x0110:
            java.util.NoSuchElementException r10 = new java.util.NoSuchElementException     // Catch:{ all -> 0x009a }
            java.lang.String r11 = "ReceiveChannel is empty."
            r10.<init>(r11)     // Catch:{ all -> 0x009a }
            java.lang.Throwable r10 = (java.lang.Throwable) r10     // Catch:{ all -> 0x009a }
            throw r10     // Catch:{ all -> 0x009a }
        L_0x011a:
            r11 = move-exception
            r6 = r10
            r10 = r11
        L_0x011d:
            throw r10     // Catch:{ all -> 0x011e }
        L_0x011e:
            r11 = move-exception
            r6.cancel(r10)
            throw r11
        L_0x0123:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r10 = r11.exception
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.single(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ec A[Catch:{ all -> 0x0166 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ed A[Catch:{ all -> 0x0166 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0109 A[Catch:{ all -> 0x0166 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0135 A[Catch:{ all -> 0x0166 }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0159  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x015c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object single(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r19, kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r20, kotlin.coroutines.Continuation<? super E> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$3 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$3 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$single$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00ab
            if (r3 == r5) goto L_0x0073
            if (r3 != r4) goto L_0x006b
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$BooleanRef r10 = (kotlin.jvm.internal.Ref.BooleanRef) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r11 = (kotlin.jvm.internal.Ref.ObjectRef) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a6 }
            if (r14 != 0) goto L_0x0066
            r15 = r8
            r8 = r1
            r1 = r9
            r9 = r2
            r2 = r15
        L_0x0058:
            r16 = r12
            r12 = r3
            r3 = r6
            r6 = r16
            r17 = r10
            r10 = r7
            r7 = r11
            r11 = r17
            goto L_0x0129
        L_0x0066:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a6 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a6 }
            throw r0     // Catch:{ all -> 0x00a6 }
        L_0x006b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0073:
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$BooleanRef r10 = (kotlin.jvm.internal.Ref.BooleanRef) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r11 = (kotlin.jvm.internal.Ref.ObjectRef) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a6 }
            if (r14 != 0) goto L_0x00a1
            r15 = r9
            r9 = r2
            r2 = r8
            r8 = r15
            goto L_0x0101
        L_0x00a1:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a6 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a6 }
            throw r0     // Catch:{ all -> 0x00a6 }
        L_0x00a6:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x016c
        L_0x00ab:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0179
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            kotlin.jvm.internal.Ref$BooleanRef r6 = new kotlin.jvm.internal.Ref$BooleanRef
            r6.<init>()
            r7 = 0
            r6.element = r7
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r7 = r19.iterator()     // Catch:{ all -> 0x0168 }
            r8 = r1
            r9 = r2
            r10 = r3
            r11 = r6
            r12 = r7
            r1 = r19
            r2 = r1
            r3 = r2
            r6 = r20
            r7 = r0
            r0 = r3
        L_0x00d2:
            r8.L$0 = r0     // Catch:{ all -> 0x0166 }
            r8.L$1 = r6     // Catch:{ all -> 0x0166 }
            r8.L$2 = r7     // Catch:{ all -> 0x0166 }
            r8.L$3 = r11     // Catch:{ all -> 0x0166 }
            r8.L$4 = r1     // Catch:{ all -> 0x0166 }
            r8.L$5 = r2     // Catch:{ all -> 0x0166 }
            r8.L$6 = r10     // Catch:{ all -> 0x0166 }
            r8.L$7 = r3     // Catch:{ all -> 0x0166 }
            r8.L$8 = r12     // Catch:{ all -> 0x0166 }
            r8.label = r5     // Catch:{ all -> 0x0166 }
            java.lang.Object r13 = r12.hasNext(r8)     // Catch:{ all -> 0x0166 }
            if (r13 != r9) goto L_0x00ed
            return r9
        L_0x00ed:
            r15 = r13
            r13 = r0
            r0 = r15
            r16 = r8
            r8 = r1
            r1 = r16
            r17 = r6
            r6 = r3
            r3 = r12
            r12 = r17
            r18 = r11
            r11 = r7
            r7 = r10
            r10 = r18
        L_0x0101:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0166 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0166 }
            if (r0 == 0) goto L_0x014a
            r1.L$0 = r13     // Catch:{ all -> 0x0166 }
            r1.L$1 = r12     // Catch:{ all -> 0x0166 }
            r1.L$2 = r11     // Catch:{ all -> 0x0166 }
            r1.L$3 = r10     // Catch:{ all -> 0x0166 }
            r1.L$4 = r8     // Catch:{ all -> 0x0166 }
            r1.L$5 = r2     // Catch:{ all -> 0x0166 }
            r1.L$6 = r7     // Catch:{ all -> 0x0166 }
            r1.L$7 = r6     // Catch:{ all -> 0x0166 }
            r1.L$8 = r3     // Catch:{ all -> 0x0166 }
            r1.label = r4     // Catch:{ all -> 0x0166 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0166 }
            if (r0 != r9) goto L_0x0124
            return r9
        L_0x0124:
            r15 = r8
            r8 = r1
            r1 = r15
            goto L_0x0058
        L_0x0129:
            java.lang.Object r14 = r6.invoke(r0)     // Catch:{ all -> 0x0166 }
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ all -> 0x0166 }
            boolean r14 = r14.booleanValue()     // Catch:{ all -> 0x0166 }
            if (r14 == 0) goto L_0x0148
            boolean r14 = r11.element     // Catch:{ all -> 0x0166 }
            if (r14 != 0) goto L_0x013e
            r7.element = r0     // Catch:{ all -> 0x0166 }
            r11.element = r5     // Catch:{ all -> 0x0166 }
            goto L_0x0148
        L_0x013e:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0166 }
            java.lang.String r1 = "ReceiveChannel contains more than one matching element."
            r0.<init>(r1)     // Catch:{ all -> 0x0166 }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x0166 }
            throw r0     // Catch:{ all -> 0x0166 }
        L_0x0148:
            r0 = r13
            goto L_0x00d2
        L_0x014a:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0166 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            boolean r0 = r10.element
            if (r0 == 0) goto L_0x015c
            T r0 = r11.element
            return r0
        L_0x015c:
            java.util.NoSuchElementException r0 = new java.util.NoSuchElementException
            java.lang.String r1 = "ReceiveChannel contains no element matching the predicate."
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x0166:
            r0 = move-exception
            goto L_0x016b
        L_0x0168:
            r0 = move-exception
            r2 = r19
        L_0x016b:
            r1 = r0
        L_0x016c:
            throw r1     // Catch:{ all -> 0x016d }
        L_0x016d:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x0179:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.single(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00cf A[SYNTHETIC, Splitter:B:52:0x00cf] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00f9 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object singleOrNull(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r11, kotlin.coroutines.Continuation<? super E> r12) {
        /*
            boolean r0 = r12 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$1
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L_0x009e
            if (r2 == r5) goto L_0x007d
            if (r2 == r4) goto L_0x005a
            if (r2 != r3) goto L_0x0052
            java.lang.Object r11 = r0.L$5
            java.lang.Object r1 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r1 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1
            java.lang.Object r1 = r0.L$2
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            boolean r0 = r12 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x004e }
            if (r0 != 0) goto L_0x0049
            goto L_0x00ff
        L_0x0049:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12     // Catch:{ all -> 0x004e }
            java.lang.Throwable r11 = r12.exception     // Catch:{ all -> 0x004e }
            throw r11     // Catch:{ all -> 0x004e }
        L_0x004e:
            r11 = move-exception
            r7 = r2
            goto L_0x0112
        L_0x0052:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x005a:
            java.lang.Object r11 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$2
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            java.lang.Object r5 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            boolean r8 = r12 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0079 }
            if (r8 != 0) goto L_0x0074
            goto L_0x00e5
        L_0x0074:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12     // Catch:{ all -> 0x0079 }
            java.lang.Throwable r11 = r12.exception     // Catch:{ all -> 0x0079 }
            throw r11     // Catch:{ all -> 0x0079 }
        L_0x0079:
            r11 = move-exception
            r7 = r5
            goto L_0x0112
        L_0x007d:
            java.lang.Object r11 = r0.L$4
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$2
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r12 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009b }
            if (r9 != 0) goto L_0x0096
            goto L_0x00c3
        L_0x0096:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12     // Catch:{ all -> 0x009b }
            java.lang.Throwable r11 = r12.exception     // Catch:{ all -> 0x009b }
            throw r11     // Catch:{ all -> 0x009b }
        L_0x009b:
            r11 = move-exception
            goto L_0x0112
        L_0x009e:
            boolean r2 = r12 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x0118
            r12 = r6
            java.lang.Throwable r12 = (java.lang.Throwable) r12
            kotlinx.coroutines.channels.ChannelIterator r2 = r11.iterator()     // Catch:{ all -> 0x010f }
            r0.L$0 = r11     // Catch:{ all -> 0x010f }
            r0.L$1 = r11     // Catch:{ all -> 0x010f }
            r0.L$2 = r12     // Catch:{ all -> 0x010f }
            r0.L$3 = r11     // Catch:{ all -> 0x010f }
            r0.L$4 = r2     // Catch:{ all -> 0x010f }
            r0.label = r5     // Catch:{ all -> 0x010f }
            java.lang.Object r5 = r2.hasNext(r0)     // Catch:{ all -> 0x010f }
            if (r5 != r1) goto L_0x00bc
            return r1
        L_0x00bc:
            r7 = r11
            r8 = r7
            r11 = r2
            r2 = r8
            r10 = r5
            r5 = r12
            r12 = r10
        L_0x00c3:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ all -> 0x009b }
            boolean r12 = r12.booleanValue()     // Catch:{ all -> 0x009b }
            if (r12 != 0) goto L_0x00cf
            r7.cancel(r5)
            return r6
        L_0x00cf:
            r0.L$0 = r8     // Catch:{ all -> 0x009b }
            r0.L$1 = r7     // Catch:{ all -> 0x009b }
            r0.L$2 = r5     // Catch:{ all -> 0x009b }
            r0.L$3 = r2     // Catch:{ all -> 0x009b }
            r0.L$4 = r11     // Catch:{ all -> 0x009b }
            r0.label = r4     // Catch:{ all -> 0x009b }
            java.lang.Object r12 = r11.next(r0)     // Catch:{ all -> 0x009b }
            if (r12 != r1) goto L_0x00e2
            return r1
        L_0x00e2:
            r4 = r5
            r5 = r7
            r7 = r8
        L_0x00e5:
            r0.L$0 = r7     // Catch:{ all -> 0x0079 }
            r0.L$1 = r5     // Catch:{ all -> 0x0079 }
            r0.L$2 = r4     // Catch:{ all -> 0x0079 }
            r0.L$3 = r2     // Catch:{ all -> 0x0079 }
            r0.L$4 = r11     // Catch:{ all -> 0x0079 }
            r0.L$5 = r12     // Catch:{ all -> 0x0079 }
            r0.label = r3     // Catch:{ all -> 0x0079 }
            java.lang.Object r11 = r11.hasNext(r0)     // Catch:{ all -> 0x0079 }
            if (r11 != r1) goto L_0x00fa
            return r1
        L_0x00fa:
            r1 = r4
            r2 = r5
            r10 = r12
            r12 = r11
            r11 = r10
        L_0x00ff:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ all -> 0x004e }
            boolean r12 = r12.booleanValue()     // Catch:{ all -> 0x004e }
            if (r12 == 0) goto L_0x010b
            r2.cancel(r1)
            return r6
        L_0x010b:
            r2.cancel(r1)
            return r11
        L_0x010f:
            r12 = move-exception
            r7 = r11
            r11 = r12
        L_0x0112:
            throw r11     // Catch:{ all -> 0x0113 }
        L_0x0113:
            r12 = move-exception
            r7.cancel(r11)
            throw r12
        L_0x0118:
            kotlin.Result$Failure r12 = (kotlin.Result.Failure) r12
            java.lang.Throwable r11 = r12.exception
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.singleOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f0 A[Catch:{ all -> 0x0163 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00f1 A[Catch:{ all -> 0x0163 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x010e A[Catch:{ all -> 0x0163 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x013c A[Catch:{ all -> 0x0163 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x015f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0160  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object singleOrNull(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r20, kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r21, kotlin.coroutines.Continuation<? super E> r22) {
        /*
            r0 = r22
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$3 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$3 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$singleOrNull$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 0
            r5 = 2
            r6 = 1
            if (r3 == 0) goto L_0x00af
            if (r3 == r6) goto L_0x0075
            if (r3 != r5) goto L_0x006d
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$6
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$3
            kotlin.jvm.internal.Ref$BooleanRef r11 = (kotlin.jvm.internal.Ref.BooleanRef) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r12 = (kotlin.jvm.internal.Ref.ObjectRef) r12
            java.lang.Object r13 = r1.L$1
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00aa }
            if (r15 != 0) goto L_0x0068
            r16 = r9
            r9 = r1
            r1 = r10
            r10 = r2
            r2 = r16
        L_0x005b:
            r17 = r13
            r13 = r3
            r3 = r7
            r7 = r17
            r18 = r12
            r12 = r8
            r8 = r18
            goto L_0x0130
        L_0x0068:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00aa }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00aa }
            throw r0     // Catch:{ all -> 0x00aa }
        L_0x006d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0075:
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$6
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$3
            kotlin.jvm.internal.Ref$BooleanRef r11 = (kotlin.jvm.internal.Ref.BooleanRef) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r12 = (kotlin.jvm.internal.Ref.ObjectRef) r12
            java.lang.Object r13 = r1.L$1
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00aa }
            if (r15 != 0) goto L_0x00a5
            r16 = r10
            r10 = r2
            r2 = r9
            r9 = r16
            goto L_0x0106
        L_0x00a5:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00aa }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00aa }
            throw r0     // Catch:{ all -> 0x00aa }
        L_0x00aa:
            r0 = move-exception
            r1 = r0
            r2 = r9
            goto L_0x0169
        L_0x00af:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0176
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r0.<init>()
            r0.element = r4
            kotlin.jvm.internal.Ref$BooleanRef r3 = new kotlin.jvm.internal.Ref$BooleanRef
            r3.<init>()
            r7 = 0
            r3.element = r7
            r7 = r4
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            kotlinx.coroutines.channels.ChannelIterator r8 = r20.iterator()     // Catch:{ all -> 0x0165 }
            r9 = r1
            r10 = r2
            r11 = r3
            r12 = r7
            r13 = r8
            r1 = r20
            r2 = r1
            r3 = r2
            r7 = r21
            r8 = r0
            r0 = r3
        L_0x00d6:
            r9.L$0 = r0     // Catch:{ all -> 0x0163 }
            r9.L$1 = r7     // Catch:{ all -> 0x0163 }
            r9.L$2 = r8     // Catch:{ all -> 0x0163 }
            r9.L$3 = r11     // Catch:{ all -> 0x0163 }
            r9.L$4 = r1     // Catch:{ all -> 0x0163 }
            r9.L$5 = r2     // Catch:{ all -> 0x0163 }
            r9.L$6 = r12     // Catch:{ all -> 0x0163 }
            r9.L$7 = r3     // Catch:{ all -> 0x0163 }
            r9.L$8 = r13     // Catch:{ all -> 0x0163 }
            r9.label = r6     // Catch:{ all -> 0x0163 }
            java.lang.Object r14 = r13.hasNext(r9)     // Catch:{ all -> 0x0163 }
            if (r14 != r10) goto L_0x00f1
            return r10
        L_0x00f1:
            r16 = r14
            r14 = r0
            r0 = r16
            r17 = r9
            r9 = r1
            r1 = r17
            r18 = r7
            r7 = r3
            r3 = r13
            r13 = r18
            r19 = r12
            r12 = r8
            r8 = r19
        L_0x0106:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0163 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0163 }
            if (r0 == 0) goto L_0x0150
            r1.L$0 = r14     // Catch:{ all -> 0x0163 }
            r1.L$1 = r13     // Catch:{ all -> 0x0163 }
            r1.L$2 = r12     // Catch:{ all -> 0x0163 }
            r1.L$3 = r11     // Catch:{ all -> 0x0163 }
            r1.L$4 = r9     // Catch:{ all -> 0x0163 }
            r1.L$5 = r2     // Catch:{ all -> 0x0163 }
            r1.L$6 = r8     // Catch:{ all -> 0x0163 }
            r1.L$7 = r7     // Catch:{ all -> 0x0163 }
            r1.L$8 = r3     // Catch:{ all -> 0x0163 }
            r1.label = r5     // Catch:{ all -> 0x0163 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0163 }
            if (r0 != r10) goto L_0x0129
            return r10
        L_0x0129:
            r16 = r9
            r9 = r1
            r1 = r16
            goto L_0x005b
        L_0x0130:
            java.lang.Object r15 = r7.invoke(r0)     // Catch:{ all -> 0x0163 }
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ all -> 0x0163 }
            boolean r15 = r15.booleanValue()     // Catch:{ all -> 0x0163 }
            if (r15 == 0) goto L_0x014e
            boolean r15 = r11.element     // Catch:{ all -> 0x0163 }
            if (r15 == 0) goto L_0x014a
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r12)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            return r4
        L_0x014a:
            r8.element = r0     // Catch:{ all -> 0x0163 }
            r11.element = r6     // Catch:{ all -> 0x0163 }
        L_0x014e:
            r0 = r14
            goto L_0x00d6
        L_0x0150:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0163 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            r2.cancel(r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            boolean r0 = r11.element
            if (r0 != 0) goto L_0x0160
            return r4
        L_0x0160:
            T r0 = r12.element
            return r0
        L_0x0163:
            r0 = move-exception
            goto L_0x0168
        L_0x0165:
            r0 = move-exception
            r2 = r20
        L_0x0168:
            r1 = r0
        L_0x0169:
            throw r1     // Catch:{ all -> 0x016a }
        L_0x016a:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            throw r3
        L_0x0176:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.singleOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ ReceiveChannel drop$default(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.drop(receiveChannel, i, coroutineContext);
    }

    public static final <E> ReceiveChannel<E> drop(ReceiveChannel<? extends E> receiveChannel, int i, CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$drop$1(receiveChannel, i, (Continuation) null), 2, (Object) null);
    }

    public static /* synthetic */ ReceiveChannel dropWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.dropWhile(receiveChannel, coroutineContext, function2);
    }

    public static final <E> ReceiveChannel<E> dropWhile(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "predicate");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$dropWhile$1(receiveChannel, function2, (Continuation) null), 2, (Object) null);
    }

    public static /* synthetic */ ReceiveChannel filter$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.filter(receiveChannel, coroutineContext, function2);
    }

    public static final <E> ReceiveChannel<E> filter(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "predicate");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$filter$1(receiveChannel, function2, (Continuation) null), 2, (Object) null);
    }

    public static /* synthetic */ ReceiveChannel filterIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.filterIndexed(receiveChannel, coroutineContext, function3);
    }

    public static final <E> ReceiveChannel<E> filterIndexed(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function3<? super Integer, ? super E, ? super Continuation<? super Boolean>, ? extends Object> function3) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function3, "predicate");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$filterIndexed$1(receiveChannel, function3, (Continuation) null), 2, (Object) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f3 A[Catch:{ all -> 0x0171 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x010e A[Catch:{ all -> 0x0171 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x015c A[Catch:{ all -> 0x0171 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends java.util.Collection<? super E>> java.lang.Object filterIndexedTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r19, C r20, kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super E, java.lang.Boolean> r21, kotlin.coroutines.Continuation<? super C> r22) {
        /*
            r0 = r22
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00b4
            if (r3 == r5) goto L_0x0076
            if (r3 != r4) goto L_0x006e
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$7
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            java.lang.Object r13 = r1.L$1
            java.util.Collection r13 = (java.util.Collection) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00af }
            if (r15 != 0) goto L_0x0069
            r16 = r10
            r10 = r1
            r1 = r11
            r11 = r2
            r2 = r9
            r9 = r16
            r17 = r13
            r13 = r3
            r3 = r8
            r8 = r12
            r12 = r7
            r7 = r17
            goto L_0x0139
        L_0x0069:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00af }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00af }
            throw r0     // Catch:{ all -> 0x00af }
        L_0x006e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0076:
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$7
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            java.lang.Object r13 = r1.L$1
            java.util.Collection r13 = (java.util.Collection) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00af }
            if (r15 != 0) goto L_0x00aa
            r16 = r13
            r13 = r3
            r3 = r8
            r8 = r16
            goto L_0x0106
        L_0x00aa:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00af }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00af }
            throw r0     // Catch:{ all -> 0x00af }
        L_0x00af:
            r0 = move-exception
            r1 = r0
            r3 = r8
            goto L_0x0177
        L_0x00b4:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0185
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r6 = r19.iterator()     // Catch:{ all -> 0x0173 }
            r7 = r20
            r8 = r21
            r9 = r0
            r10 = r1
            r11 = r2
            r12 = r3
            r13 = r6
            r0 = r19
            r1 = r0
            r2 = r1
            r3 = r2
            r6 = r3
        L_0x00d6:
            r10.L$0 = r0     // Catch:{ all -> 0x0171 }
            r10.L$1 = r7     // Catch:{ all -> 0x0171 }
            r10.L$2 = r8     // Catch:{ all -> 0x0171 }
            r10.L$3 = r1     // Catch:{ all -> 0x0171 }
            r10.L$4 = r9     // Catch:{ all -> 0x0171 }
            r10.L$5 = r2     // Catch:{ all -> 0x0171 }
            r10.L$6 = r3     // Catch:{ all -> 0x0171 }
            r10.L$7 = r12     // Catch:{ all -> 0x0171 }
            r10.L$8 = r6     // Catch:{ all -> 0x0171 }
            r10.L$9 = r13     // Catch:{ all -> 0x0171 }
            r10.label = r5     // Catch:{ all -> 0x0171 }
            java.lang.Object r14 = r13.hasNext(r10)     // Catch:{ all -> 0x0171 }
            if (r14 != r11) goto L_0x00f3
            return r11
        L_0x00f3:
            r16 = r14
            r14 = r0
            r0 = r16
            r17 = r11
            r11 = r1
            r1 = r10
            r10 = r9
            r9 = r2
            r2 = r17
            r18 = r8
            r8 = r7
            r7 = r12
            r12 = r18
        L_0x0106:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0171 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0171 }
            if (r0 == 0) goto L_0x0164
            r1.L$0 = r14     // Catch:{ all -> 0x0171 }
            r1.L$1 = r8     // Catch:{ all -> 0x0171 }
            r1.L$2 = r12     // Catch:{ all -> 0x0171 }
            r1.L$3 = r11     // Catch:{ all -> 0x0171 }
            r1.L$4 = r10     // Catch:{ all -> 0x0171 }
            r1.L$5 = r9     // Catch:{ all -> 0x0171 }
            r1.L$6 = r3     // Catch:{ all -> 0x0171 }
            r1.L$7 = r7     // Catch:{ all -> 0x0171 }
            r1.L$8 = r6     // Catch:{ all -> 0x0171 }
            r1.L$9 = r13     // Catch:{ all -> 0x0171 }
            r1.label = r4     // Catch:{ all -> 0x0171 }
            java.lang.Object r0 = r13.next(r1)     // Catch:{ all -> 0x0171 }
            if (r0 != r2) goto L_0x012b
            return r2
        L_0x012b:
            r16 = r10
            r10 = r1
            r1 = r11
            r11 = r2
            r2 = r9
            r9 = r16
            r17 = r12
            r12 = r7
            r7 = r8
            r8 = r17
        L_0x0139:
            kotlin.collections.IndexedValue r15 = new kotlin.collections.IndexedValue     // Catch:{ all -> 0x0171 }
            int r4 = r9.element     // Catch:{ all -> 0x0171 }
            int r5 = r4 + 1
            r9.element = r5     // Catch:{ all -> 0x0171 }
            r15.<init>(r4, r0)     // Catch:{ all -> 0x0171 }
            int r0 = r15.component1()     // Catch:{ all -> 0x0171 }
            java.lang.Object r4 = r15.component2()     // Catch:{ all -> 0x0171 }
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)     // Catch:{ all -> 0x0171 }
            java.lang.Object r0 = r8.invoke(r0, r4)     // Catch:{ all -> 0x0171 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0171 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0171 }
            if (r0 == 0) goto L_0x015f
            r7.add(r4)     // Catch:{ all -> 0x0171 }
        L_0x015f:
            r0 = r14
            r4 = 2
            r5 = 1
            goto L_0x00d6
        L_0x0164:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0171 }
            r1 = 1
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r3.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r8
        L_0x0171:
            r0 = move-exception
            goto L_0x0176
        L_0x0173:
            r0 = move-exception
            r3 = r19
        L_0x0176:
            r1 = r0
        L_0x0177:
            throw r1     // Catch:{ all -> 0x0178 }
        L_0x0178:
            r0 = move-exception
            r2 = r0
            r4 = 1
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            r3.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r2
        L_0x0185:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterIndexedTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0134 A[Catch:{ all -> 0x01f3 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0135 A[Catch:{ all -> 0x01f3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x014d A[Catch:{ all -> 0x01f3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x01bf A[Catch:{ all -> 0x01f3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends kotlinx.coroutines.channels.SendChannel<? super E>> java.lang.Object filterIndexedTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, C r19, kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super E, java.lang.Boolean> r20, kotlin.coroutines.Continuation<? super C> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$3 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$3 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexedTo$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 3
            r5 = 2
            r6 = 1
            if (r3 == 0) goto L_0x00f6
            if (r3 == r6) goto L_0x00b8
            if (r3 == r5) goto L_0x007b
            if (r3 != r4) goto L_0x0073
            java.lang.Object r3 = r1.L$13
            int r3 = r1.I$0
            java.lang.Object r3 = r1.L$12
            kotlin.collections.IndexedValue r3 = (kotlin.collections.IndexedValue) r3
            java.lang.Object r3 = r1.L$11
            java.lang.Object r3 = r1.L$10
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$7
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$4
            kotlin.jvm.internal.Ref$IntRef r11 = (kotlin.jvm.internal.Ref.IntRef) r11
            java.lang.Object r12 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r1.L$2
            kotlin.jvm.functions.Function2 r13 = (kotlin.jvm.functions.Function2) r13
            java.lang.Object r14 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r14 = (kotlinx.coroutines.channels.SendChannel) r14
            java.lang.Object r15 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r15 = (kotlinx.coroutines.channels.ReceiveChannel) r15
            boolean r4 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x006e }
            if (r4 != 0) goto L_0x0069
            r0 = 3
            goto L_0x01c3
        L_0x0069:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x006e }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x006e }
            throw r0     // Catch:{ all -> 0x006e }
        L_0x006e:
            r0 = move-exception
            r1 = r0
            r3 = r9
            goto L_0x01f9
        L_0x0073:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x007b:
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r4 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r7 = r1.L$7
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            java.lang.Object r13 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r13 = (kotlinx.coroutines.channels.SendChannel) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00f1 }
            if (r15 != 0) goto L_0x00b3
            r15 = r14
            r14 = r13
            r13 = r12
            r12 = r11
            r11 = r3
            r3 = r8
        L_0x00ad:
            r8 = r7
            r7 = r4
            r4 = r10
            r10 = r9
            goto L_0x0170
        L_0x00b3:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00f1 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00f1 }
            throw r0     // Catch:{ all -> 0x00f1 }
        L_0x00b8:
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r4 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r7 = r1.L$7
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            java.lang.Object r13 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r13 = (kotlinx.coroutines.channels.SendChannel) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00f1 }
            if (r15 != 0) goto L_0x00ec
            r16 = r11
            r11 = r3
            r3 = r8
            r8 = r16
            goto L_0x0145
        L_0x00ec:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00f1 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00f1 }
            throw r0     // Catch:{ all -> 0x00f1 }
        L_0x00f1:
            r0 = move-exception
            r1 = r0
            r3 = r8
            goto L_0x01f9
        L_0x00f6:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0207
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r4 = r18.iterator()     // Catch:{ all -> 0x01f5 }
            r7 = r19
            r8 = r20
            r13 = r0
            r9 = r1
            r10 = r2
            r12 = r3
            r11 = r4
            r0 = r18
            r1 = r0
            r2 = r1
            r3 = r2
            r4 = r3
        L_0x0118:
            r9.L$0 = r0     // Catch:{ all -> 0x01f3 }
            r9.L$1 = r7     // Catch:{ all -> 0x01f3 }
            r9.L$2 = r8     // Catch:{ all -> 0x01f3 }
            r9.L$3 = r1     // Catch:{ all -> 0x01f3 }
            r9.L$4 = r13     // Catch:{ all -> 0x01f3 }
            r9.L$5 = r2     // Catch:{ all -> 0x01f3 }
            r9.L$6 = r3     // Catch:{ all -> 0x01f3 }
            r9.L$7 = r12     // Catch:{ all -> 0x01f3 }
            r9.L$8 = r4     // Catch:{ all -> 0x01f3 }
            r9.L$9 = r11     // Catch:{ all -> 0x01f3 }
            r9.label = r6     // Catch:{ all -> 0x01f3 }
            java.lang.Object r14 = r11.hasNext(r9)     // Catch:{ all -> 0x01f3 }
            if (r14 != r10) goto L_0x0135
            return r10
        L_0x0135:
            r16 = r14
            r14 = r0
            r0 = r16
            r17 = r8
            r8 = r1
            r1 = r9
            r9 = r2
            r2 = r10
            r10 = r13
            r13 = r7
            r7 = r12
            r12 = r17
        L_0x0145:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01f3 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01f3 }
            if (r0 == 0) goto L_0x01e6
            r1.L$0 = r14     // Catch:{ all -> 0x01f3 }
            r1.L$1 = r13     // Catch:{ all -> 0x01f3 }
            r1.L$2 = r12     // Catch:{ all -> 0x01f3 }
            r1.L$3 = r8     // Catch:{ all -> 0x01f3 }
            r1.L$4 = r10     // Catch:{ all -> 0x01f3 }
            r1.L$5 = r9     // Catch:{ all -> 0x01f3 }
            r1.L$6 = r3     // Catch:{ all -> 0x01f3 }
            r1.L$7 = r7     // Catch:{ all -> 0x01f3 }
            r1.L$8 = r4     // Catch:{ all -> 0x01f3 }
            r1.L$9 = r11     // Catch:{ all -> 0x01f3 }
            r1.label = r5     // Catch:{ all -> 0x01f3 }
            java.lang.Object r0 = r11.next(r1)     // Catch:{ all -> 0x01f3 }
            if (r0 != r2) goto L_0x016a
            return r2
        L_0x016a:
            r15 = r14
            r14 = r13
            r13 = r12
            r12 = r8
            goto L_0x00ad
        L_0x0170:
            kotlin.collections.IndexedValue r9 = new kotlin.collections.IndexedValue     // Catch:{ all -> 0x01f3 }
            int r5 = r4.element     // Catch:{ all -> 0x01f3 }
            int r6 = r5 + 1
            r4.element = r6     // Catch:{ all -> 0x01f3 }
            r9.<init>(r5, r0)     // Catch:{ all -> 0x01f3 }
            int r5 = r9.component1()     // Catch:{ all -> 0x01f3 }
            java.lang.Object r6 = r9.component2()     // Catch:{ all -> 0x01f3 }
            r18 = r2
            java.lang.Integer r2 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)     // Catch:{ all -> 0x01f3 }
            java.lang.Object r2 = r13.invoke(r2, r6)     // Catch:{ all -> 0x01f3 }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ all -> 0x01f3 }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x01f3 }
            if (r2 == 0) goto L_0x01d5
            r1.L$0 = r15     // Catch:{ all -> 0x01f3 }
            r1.L$1 = r14     // Catch:{ all -> 0x01f3 }
            r1.L$2 = r13     // Catch:{ all -> 0x01f3 }
            r1.L$3 = r12     // Catch:{ all -> 0x01f3 }
            r1.L$4 = r4     // Catch:{ all -> 0x01f3 }
            r1.L$5 = r10     // Catch:{ all -> 0x01f3 }
            r1.L$6 = r3     // Catch:{ all -> 0x01f3 }
            r1.L$7 = r8     // Catch:{ all -> 0x01f3 }
            r1.L$8 = r7     // Catch:{ all -> 0x01f3 }
            r1.L$9 = r11     // Catch:{ all -> 0x01f3 }
            r1.L$10 = r0     // Catch:{ all -> 0x01f3 }
            r1.L$11 = r0     // Catch:{ all -> 0x01f3 }
            r1.L$12 = r9     // Catch:{ all -> 0x01f3 }
            r1.I$0 = r5     // Catch:{ all -> 0x01f3 }
            r1.L$13 = r6     // Catch:{ all -> 0x01f3 }
            r0 = 3
            r1.label = r0     // Catch:{ all -> 0x01f3 }
            java.lang.Object r2 = r14.send(r6, r1)     // Catch:{ all -> 0x01f3 }
            r5 = r18
            if (r2 != r5) goto L_0x01bf
            return r5
        L_0x01bf:
            r9 = r3
            r2 = r5
            r3 = r11
            r11 = r4
        L_0x01c3:
            r4 = r7
            r7 = r14
            r16 = r9
            r9 = r1
            r1 = r12
            r12 = r8
            r8 = r13
            r13 = r11
            r11 = r3
            r3 = r16
            r17 = r10
            r10 = r2
            r2 = r17
            goto L_0x01e1
        L_0x01d5:
            r5 = r18
            r0 = 3
            r9 = r1
            r2 = r10
            r1 = r12
            r10 = r5
            r12 = r8
            r8 = r13
            r13 = r4
            r4 = r7
            r7 = r14
        L_0x01e1:
            r0 = r15
            r5 = 2
            r6 = 1
            goto L_0x0118
        L_0x01e6:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x01f3 }
            r1 = 1
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r3.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r13
        L_0x01f3:
            r0 = move-exception
            goto L_0x01f8
        L_0x01f5:
            r0 = move-exception
            r3 = r18
        L_0x01f8:
            r1 = r0
        L_0x01f9:
            throw r1     // Catch:{ all -> 0x01fa }
        L_0x01fa:
            r0 = move-exception
            r2 = r0
            r4 = 1
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            r3.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r2
        L_0x0207:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterIndexedTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ ReceiveChannel filterNot$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.filterNot(receiveChannel, coroutineContext, function2);
    }

    public static final <E> ReceiveChannel<E> filterNot(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "predicate");
        return ChannelsKt.filter(receiveChannel, coroutineContext, new ChannelsKt__Channels_commonKt$filterNot$1(function2, (Continuation) null));
    }

    public static final <E> ReceiveChannel<E> filterNotNull(ReceiveChannel<? extends E> receiveChannel) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        ReceiveChannel<E> filter$default = filter$default(receiveChannel, (CoroutineContext) null, new ChannelsKt__Channels_commonKt$filterNotNull$1((Continuation) null), 1, (Object) null);
        if (filter$default != null) {
            return filter$default;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveChannel<E>");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b9 A[Catch:{ all -> 0x00f8 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ba A[Catch:{ all -> 0x00f8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ca A[Catch:{ all -> 0x00f8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ea A[SYNTHETIC, Splitter:B:47:0x00ea] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends java.util.Collection<? super E>> java.lang.Object filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, C r14, kotlin.coroutines.Continuation<? super C> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$1
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0090
            if (r2 == r4) goto L_0x0062
            if (r2 != r3) goto L_0x005a
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            java.util.Collection r7 = (java.util.Collection) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008c }
            if (r9 != 0) goto L_0x0055
            r10 = r8
            r8 = r13
            r13 = r10
        L_0x004c:
            r11 = r0
            r0 = r14
            r14 = r6
            r6 = r11
            r12 = r7
            r7 = r1
            r1 = r12
            goto L_0x00e8
        L_0x0055:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x008c }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x008c }
            throw r13     // Catch:{ all -> 0x008c }
        L_0x005a:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0062:
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            java.util.Collection r7 = (java.util.Collection) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008c }
            if (r9 != 0) goto L_0x0087
            r10 = r8
            r8 = r15
            r15 = r5
            r5 = r10
            goto L_0x00c2
        L_0x0087:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x008c }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x008c }
            throw r13     // Catch:{ all -> 0x008c }
        L_0x008c:
            r13 = move-exception
            r15 = r5
            goto L_0x00fd
        L_0x0090:
            boolean r2 = r15 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x0103
            r15 = 0
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            kotlinx.coroutines.channels.ChannelIterator r2 = r13.iterator()     // Catch:{ all -> 0x00fa }
            r5 = r0
            r6 = r1
            r7 = r2
            r0 = r13
            r1 = r14
            r2 = r15
            r14 = r0
            r15 = r14
        L_0x00a3:
            r5.L$0 = r13     // Catch:{ all -> 0x00f8 }
            r5.L$1 = r1     // Catch:{ all -> 0x00f8 }
            r5.L$2 = r14     // Catch:{ all -> 0x00f8 }
            r5.L$3 = r15     // Catch:{ all -> 0x00f8 }
            r5.L$4 = r2     // Catch:{ all -> 0x00f8 }
            r5.L$5 = r0     // Catch:{ all -> 0x00f8 }
            r5.L$6 = r7     // Catch:{ all -> 0x00f8 }
            r5.label = r4     // Catch:{ all -> 0x00f8 }
            java.lang.Object r8 = r7.hasNext(r5)     // Catch:{ all -> 0x00f8 }
            if (r8 != r6) goto L_0x00ba
            return r6
        L_0x00ba:
            r10 = r5
            r5 = r13
            r13 = r7
            r7 = r1
            r1 = r6
            r6 = r14
            r14 = r0
            r0 = r10
        L_0x00c2:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x00f8 }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x00f8 }
            if (r8 == 0) goto L_0x00f2
            r0.L$0 = r5     // Catch:{ all -> 0x00f8 }
            r0.L$1 = r7     // Catch:{ all -> 0x00f8 }
            r0.L$2 = r6     // Catch:{ all -> 0x00f8 }
            r0.L$3 = r15     // Catch:{ all -> 0x00f8 }
            r0.L$4 = r2     // Catch:{ all -> 0x00f8 }
            r0.L$5 = r14     // Catch:{ all -> 0x00f8 }
            r0.L$6 = r13     // Catch:{ all -> 0x00f8 }
            r0.label = r3     // Catch:{ all -> 0x00f8 }
            java.lang.Object r8 = r13.next(r0)     // Catch:{ all -> 0x00f8 }
            if (r8 != r1) goto L_0x00e1
            return r1
        L_0x00e1:
            r10 = r8
            r8 = r13
            r13 = r5
            r5 = r15
            r15 = r10
            goto L_0x004c
        L_0x00e8:
            if (r15 == 0) goto L_0x00ed
            r1.add(r15)     // Catch:{ all -> 0x008c }
        L_0x00ed:
            r15 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            goto L_0x00a3
        L_0x00f2:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00f8 }
            r15.cancel(r2)
            return r7
        L_0x00f8:
            r13 = move-exception
            goto L_0x00fd
        L_0x00fa:
            r14 = move-exception
            r15 = r13
            r13 = r14
        L_0x00fd:
            throw r13     // Catch:{ all -> 0x00fe }
        L_0x00fe:
            r14 = move-exception
            r15.cancel(r13)
            throw r14
        L_0x0103:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15
            java.lang.Throwable r13 = r15.exception
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e1 A[Catch:{ all -> 0x0142 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f2 A[Catch:{ all -> 0x0142 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0126 A[Catch:{ all -> 0x0142 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends kotlinx.coroutines.channels.SendChannel<? super E>> java.lang.Object filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, C r14, kotlin.coroutines.Continuation<? super C> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$3
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotNullTo$3
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x00b6
            if (r2 == r5) goto L_0x0089
            if (r2 == r4) goto L_0x005f
            if (r2 != r3) goto L_0x0057
            java.lang.Object r13 = r0.L$8
            java.lang.Object r13 = r0.L$7
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.channels.SendChannel r8 = (kotlinx.coroutines.channels.SendChannel) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            boolean r10 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00b2 }
            if (r10 != 0) goto L_0x0052
            goto L_0x0129
        L_0x0052:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x00b2 }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x00b2 }
            throw r13     // Catch:{ all -> 0x00b2 }
        L_0x0057:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x005f:
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.channels.SendChannel r8 = (kotlinx.coroutines.channels.SendChannel) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            boolean r10 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00b2 }
            if (r10 != 0) goto L_0x0084
            r11 = r6
            r6 = r0
            r0 = r11
            goto L_0x0109
        L_0x0084:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x00b2 }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x00b2 }
            throw r13     // Catch:{ all -> 0x00b2 }
        L_0x0089:
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.channels.SendChannel r8 = (kotlinx.coroutines.channels.SendChannel) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            boolean r10 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00b2 }
            if (r10 != 0) goto L_0x00ad
            r11 = r6
            r6 = r0
            r0 = r11
            goto L_0x00ea
        L_0x00ad:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x00b2 }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x00b2 }
            throw r13     // Catch:{ all -> 0x00b2 }
        L_0x00b2:
            r13 = move-exception
            r0 = r6
            goto L_0x0147
        L_0x00b6:
            boolean r2 = r15 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x014d
            r15 = 0
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            kotlinx.coroutines.channels.ChannelIterator r2 = r13.iterator()     // Catch:{ all -> 0x0144 }
            r8 = r15
            r6 = r0
            r7 = r1
            r15 = r13
            r0 = r15
            r1 = r0
            r13 = r2
            r2 = r14
            r14 = r1
        L_0x00ca:
            r6.L$0 = r14     // Catch:{ all -> 0x0142 }
            r6.L$1 = r2     // Catch:{ all -> 0x0142 }
            r6.L$2 = r15     // Catch:{ all -> 0x0142 }
            r6.L$3 = r0     // Catch:{ all -> 0x0142 }
            r6.L$4 = r8     // Catch:{ all -> 0x0142 }
            r6.L$5 = r1     // Catch:{ all -> 0x0142 }
            r6.L$6 = r13     // Catch:{ all -> 0x0142 }
            r6.label = r5     // Catch:{ all -> 0x0142 }
            java.lang.Object r9 = r13.hasNext(r6)     // Catch:{ all -> 0x0142 }
            if (r9 != r7) goto L_0x00e1
            return r7
        L_0x00e1:
            r11 = r9
            r9 = r14
            r14 = r1
            r1 = r7
            r7 = r15
            r15 = r11
            r12 = r8
            r8 = r2
            r2 = r12
        L_0x00ea:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ all -> 0x0142 }
            boolean r15 = r15.booleanValue()     // Catch:{ all -> 0x0142 }
            if (r15 == 0) goto L_0x013c
            r6.L$0 = r9     // Catch:{ all -> 0x0142 }
            r6.L$1 = r8     // Catch:{ all -> 0x0142 }
            r6.L$2 = r7     // Catch:{ all -> 0x0142 }
            r6.L$3 = r0     // Catch:{ all -> 0x0142 }
            r6.L$4 = r2     // Catch:{ all -> 0x0142 }
            r6.L$5 = r14     // Catch:{ all -> 0x0142 }
            r6.L$6 = r13     // Catch:{ all -> 0x0142 }
            r6.label = r4     // Catch:{ all -> 0x0142 }
            java.lang.Object r15 = r13.next(r6)     // Catch:{ all -> 0x0142 }
            if (r15 != r1) goto L_0x0109
            return r1
        L_0x0109:
            if (r15 == 0) goto L_0x0134
            r6.L$0 = r9     // Catch:{ all -> 0x0142 }
            r6.L$1 = r8     // Catch:{ all -> 0x0142 }
            r6.L$2 = r7     // Catch:{ all -> 0x0142 }
            r6.L$3 = r0     // Catch:{ all -> 0x0142 }
            r6.L$4 = r2     // Catch:{ all -> 0x0142 }
            r6.L$5 = r14     // Catch:{ all -> 0x0142 }
            r6.L$6 = r13     // Catch:{ all -> 0x0142 }
            r6.L$7 = r15     // Catch:{ all -> 0x0142 }
            r6.L$8 = r15     // Catch:{ all -> 0x0142 }
            r6.label = r3     // Catch:{ all -> 0x0142 }
            java.lang.Object r15 = r8.send(r15, r6)     // Catch:{ all -> 0x0142 }
            if (r15 != r1) goto L_0x0126
            return r1
        L_0x0126:
            r11 = r6
            r6 = r0
            r0 = r11
        L_0x0129:
            r15 = r7
            r7 = r1
            r1 = r14
            r14 = r9
            r11 = r6
            r6 = r0
            r0 = r11
            r12 = r8
            r8 = r2
            r2 = r12
            goto L_0x00ca
        L_0x0134:
            r15 = r7
            r7 = r1
            r1 = r14
            r14 = r9
            r11 = r8
            r8 = r2
            r2 = r11
            goto L_0x00ca
        L_0x013c:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0142 }
            r0.cancel(r2)
            return r8
        L_0x0142:
            r13 = move-exception
            goto L_0x0147
        L_0x0144:
            r14 = move-exception
            r0 = r13
            r13 = r14
        L_0x0147:
            throw r13     // Catch:{ all -> 0x0148 }
        L_0x0148:
            r14 = move-exception
            r0.cancel(r13)
            throw r14
        L_0x014d:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15
            java.lang.Throwable r13 = r15.exception
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d0 A[Catch:{ all -> 0x012b }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e9 A[Catch:{ all -> 0x012b }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x011a A[Catch:{ all -> 0x012b }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends java.util.Collection<? super E>> java.lang.Object filterNotTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, C r19, kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r20, kotlin.coroutines.Continuation<? super C> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x009f
            if (r3 == r5) goto L_0x006c
            if (r3 != r4) goto L_0x0064
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$1
            java.util.Collection r11 = (java.util.Collection) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009a }
            if (r13 != 0) goto L_0x005f
            r14 = r9
            r9 = r1
            r1 = r14
            r15 = r10
            r10 = r2
            r2 = r8
            r8 = r7
            r7 = r15
            r16 = r11
            r11 = r3
            r3 = r6
            r6 = r16
            goto L_0x010e
        L_0x005f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009a }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009a }
            throw r0     // Catch:{ all -> 0x009a }
        L_0x0064:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006c:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$1
            java.util.Collection r11 = (java.util.Collection) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009a }
            if (r13 != 0) goto L_0x0095
            r14 = r10
            r10 = r2
            r2 = r8
            r8 = r14
            goto L_0x00e1
        L_0x0095:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009a }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009a }
            throw r0     // Catch:{ all -> 0x009a }
        L_0x009a:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x0131
        L_0x009f:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x013e
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlinx.coroutines.channels.ChannelIterator r3 = r18.iterator()     // Catch:{ all -> 0x012d }
            r6 = r19
            r7 = r20
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r0 = r18
            r1 = r0
            r2 = r1
            r3 = r2
        L_0x00b7:
            r9.L$0 = r0     // Catch:{ all -> 0x012b }
            r9.L$1 = r6     // Catch:{ all -> 0x012b }
            r9.L$2 = r7     // Catch:{ all -> 0x012b }
            r9.L$3 = r1     // Catch:{ all -> 0x012b }
            r9.L$4 = r2     // Catch:{ all -> 0x012b }
            r9.L$5 = r8     // Catch:{ all -> 0x012b }
            r9.L$6 = r3     // Catch:{ all -> 0x012b }
            r9.L$7 = r11     // Catch:{ all -> 0x012b }
            r9.label = r5     // Catch:{ all -> 0x012b }
            java.lang.Object r12 = r11.hasNext(r9)     // Catch:{ all -> 0x012b }
            if (r12 != r10) goto L_0x00d0
            return r10
        L_0x00d0:
            r14 = r12
            r12 = r0
            r0 = r14
            r15 = r9
            r9 = r1
            r1 = r15
            r16 = r6
            r6 = r3
            r3 = r11
            r11 = r16
            r17 = r8
            r8 = r7
            r7 = r17
        L_0x00e1:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x012b }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x012b }
            if (r0 == 0) goto L_0x011f
            r1.L$0 = r12     // Catch:{ all -> 0x012b }
            r1.L$1 = r11     // Catch:{ all -> 0x012b }
            r1.L$2 = r8     // Catch:{ all -> 0x012b }
            r1.L$3 = r9     // Catch:{ all -> 0x012b }
            r1.L$4 = r2     // Catch:{ all -> 0x012b }
            r1.L$5 = r7     // Catch:{ all -> 0x012b }
            r1.L$6 = r6     // Catch:{ all -> 0x012b }
            r1.L$7 = r3     // Catch:{ all -> 0x012b }
            r1.label = r4     // Catch:{ all -> 0x012b }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x012b }
            if (r0 != r10) goto L_0x0102
            return r10
        L_0x0102:
            r14 = r9
            r9 = r1
            r1 = r14
            r15 = r11
            r11 = r3
            r3 = r6
            r6 = r15
            r16 = r8
            r8 = r7
            r7 = r16
        L_0x010e:
            java.lang.Object r13 = r7.invoke(r0)     // Catch:{ all -> 0x012b }
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ all -> 0x012b }
            boolean r13 = r13.booleanValue()     // Catch:{ all -> 0x012b }
            if (r13 != 0) goto L_0x011d
            r6.add(r0)     // Catch:{ all -> 0x012b }
        L_0x011d:
            r0 = r12
            goto L_0x00b7
        L_0x011f:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x012b }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            return r11
        L_0x012b:
            r0 = move-exception
            goto L_0x0130
        L_0x012d:
            r0 = move-exception
            r2 = r18
        L_0x0130:
            r1 = r0
        L_0x0131:
            throw r1     // Catch:{ all -> 0x0132 }
        L_0x0132:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x013e:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterNotTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f8 A[Catch:{ all -> 0x0177 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0110 A[Catch:{ all -> 0x0177 }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0152 A[Catch:{ all -> 0x0177 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends kotlinx.coroutines.channels.SendChannel<? super E>> java.lang.Object filterNotTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, C r19, kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r20, kotlin.coroutines.Continuation<? super C> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$3 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$3 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterNotTo$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 3
            r5 = 2
            r6 = 1
            if (r3 == 0) goto L_0x00c7
            if (r3 == r6) goto L_0x0094
            if (r3 == r5) goto L_0x0065
            if (r3 != r4) goto L_0x005d
            java.lang.Object r3 = r1.L$9
            java.lang.Object r3 = r1.L$8
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r12 = (kotlinx.coroutines.channels.SendChannel) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00c2 }
            if (r14 != 0) goto L_0x0058
            goto L_0x0156
        L_0x0058:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00c2 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00c2 }
            throw r0     // Catch:{ all -> 0x00c2 }
        L_0x005d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0065:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r12 = (kotlinx.coroutines.channels.SendChannel) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00c2 }
            if (r14 != 0) goto L_0x008f
            r15 = r10
            r10 = r2
            r2 = r9
            r9 = r15
            goto L_0x0129
        L_0x008f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00c2 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00c2 }
            throw r0     // Catch:{ all -> 0x00c2 }
        L_0x0094:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r12 = (kotlinx.coroutines.channels.SendChannel) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00c2 }
            if (r14 != 0) goto L_0x00bd
            r15 = r10
            r10 = r2
            r2 = r9
            r9 = r15
            goto L_0x0108
        L_0x00bd:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00c2 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00c2 }
            throw r0     // Catch:{ all -> 0x00c2 }
        L_0x00c2:
            r0 = move-exception
            r1 = r0
            r2 = r9
            goto L_0x017d
        L_0x00c7:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x018a
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlinx.coroutines.channels.ChannelIterator r3 = r18.iterator()     // Catch:{ all -> 0x0179 }
            r7 = r19
            r8 = r20
            r12 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r0 = r18
            r1 = r0
            r2 = r1
            r3 = r2
        L_0x00df:
            r9.L$0 = r0     // Catch:{ all -> 0x0177 }
            r9.L$1 = r7     // Catch:{ all -> 0x0177 }
            r9.L$2 = r8     // Catch:{ all -> 0x0177 }
            r9.L$3 = r1     // Catch:{ all -> 0x0177 }
            r9.L$4 = r2     // Catch:{ all -> 0x0177 }
            r9.L$5 = r12     // Catch:{ all -> 0x0177 }
            r9.L$6 = r3     // Catch:{ all -> 0x0177 }
            r9.L$7 = r11     // Catch:{ all -> 0x0177 }
            r9.label = r6     // Catch:{ all -> 0x0177 }
            java.lang.Object r13 = r11.hasNext(r9)     // Catch:{ all -> 0x0177 }
            if (r13 != r10) goto L_0x00f8
            return r10
        L_0x00f8:
            r15 = r13
            r13 = r0
            r0 = r15
            r16 = r9
            r9 = r1
            r1 = r16
            r17 = r7
            r7 = r3
            r3 = r11
            r11 = r8
            r8 = r12
            r12 = r17
        L_0x0108:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0177 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0177 }
            if (r0 == 0) goto L_0x016b
            r1.L$0 = r13     // Catch:{ all -> 0x0177 }
            r1.L$1 = r12     // Catch:{ all -> 0x0177 }
            r1.L$2 = r11     // Catch:{ all -> 0x0177 }
            r1.L$3 = r9     // Catch:{ all -> 0x0177 }
            r1.L$4 = r2     // Catch:{ all -> 0x0177 }
            r1.L$5 = r8     // Catch:{ all -> 0x0177 }
            r1.L$6 = r7     // Catch:{ all -> 0x0177 }
            r1.L$7 = r3     // Catch:{ all -> 0x0177 }
            r1.label = r5     // Catch:{ all -> 0x0177 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0177 }
            if (r0 != r10) goto L_0x0129
            return r10
        L_0x0129:
            java.lang.Object r14 = r11.invoke(r0)     // Catch:{ all -> 0x0177 }
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ all -> 0x0177 }
            boolean r14 = r14.booleanValue()     // Catch:{ all -> 0x0177 }
            if (r14 != 0) goto L_0x0166
            r1.L$0 = r13     // Catch:{ all -> 0x0177 }
            r1.L$1 = r12     // Catch:{ all -> 0x0177 }
            r1.L$2 = r11     // Catch:{ all -> 0x0177 }
            r1.L$3 = r9     // Catch:{ all -> 0x0177 }
            r1.L$4 = r2     // Catch:{ all -> 0x0177 }
            r1.L$5 = r8     // Catch:{ all -> 0x0177 }
            r1.L$6 = r7     // Catch:{ all -> 0x0177 }
            r1.L$7 = r3     // Catch:{ all -> 0x0177 }
            r1.L$8 = r0     // Catch:{ all -> 0x0177 }
            r1.L$9 = r0     // Catch:{ all -> 0x0177 }
            r1.label = r4     // Catch:{ all -> 0x0177 }
            java.lang.Object r0 = r12.send(r0, r1)     // Catch:{ all -> 0x0177 }
            if (r0 != r10) goto L_0x0152
            return r10
        L_0x0152:
            r15 = r9
            r9 = r2
            r2 = r10
            r10 = r15
        L_0x0156:
            r0 = r13
            r15 = r9
            r9 = r1
            r1 = r10
            r10 = r2
            r2 = r15
        L_0x015c:
            r16 = r11
            r11 = r3
            r3 = r7
            r7 = r12
            r12 = r8
            r8 = r16
            goto L_0x00df
        L_0x0166:
            r0 = r13
            r15 = r9
            r9 = r1
            r1 = r15
            goto L_0x015c
        L_0x016b:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0177 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            r2.cancel(r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            return r12
        L_0x0177:
            r0 = move-exception
            goto L_0x017c
        L_0x0179:
            r0 = move-exception
            r2 = r18
        L_0x017c:
            r1 = r0
        L_0x017d:
            throw r1     // Catch:{ all -> 0x017e }
        L_0x017e:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            throw r3
        L_0x018a:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterNotTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d0 A[Catch:{ all -> 0x012b }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e9 A[Catch:{ all -> 0x012b }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x011a A[Catch:{ all -> 0x012b }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends java.util.Collection<? super E>> java.lang.Object filterTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, C r19, kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r20, kotlin.coroutines.Continuation<? super C> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x009f
            if (r3 == r5) goto L_0x006c
            if (r3 != r4) goto L_0x0064
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$1
            java.util.Collection r11 = (java.util.Collection) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009a }
            if (r13 != 0) goto L_0x005f
            r14 = r9
            r9 = r1
            r1 = r14
            r15 = r10
            r10 = r2
            r2 = r8
            r8 = r7
            r7 = r15
            r16 = r11
            r11 = r3
            r3 = r6
            r6 = r16
            goto L_0x010e
        L_0x005f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009a }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009a }
            throw r0     // Catch:{ all -> 0x009a }
        L_0x0064:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006c:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$1
            java.util.Collection r11 = (java.util.Collection) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009a }
            if (r13 != 0) goto L_0x0095
            r14 = r10
            r10 = r2
            r2 = r8
            r8 = r14
            goto L_0x00e1
        L_0x0095:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009a }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009a }
            throw r0     // Catch:{ all -> 0x009a }
        L_0x009a:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x0131
        L_0x009f:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x013e
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlinx.coroutines.channels.ChannelIterator r3 = r18.iterator()     // Catch:{ all -> 0x012d }
            r6 = r19
            r7 = r20
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r0 = r18
            r1 = r0
            r2 = r1
            r3 = r2
        L_0x00b7:
            r9.L$0 = r0     // Catch:{ all -> 0x012b }
            r9.L$1 = r6     // Catch:{ all -> 0x012b }
            r9.L$2 = r7     // Catch:{ all -> 0x012b }
            r9.L$3 = r1     // Catch:{ all -> 0x012b }
            r9.L$4 = r2     // Catch:{ all -> 0x012b }
            r9.L$5 = r8     // Catch:{ all -> 0x012b }
            r9.L$6 = r3     // Catch:{ all -> 0x012b }
            r9.L$7 = r11     // Catch:{ all -> 0x012b }
            r9.label = r5     // Catch:{ all -> 0x012b }
            java.lang.Object r12 = r11.hasNext(r9)     // Catch:{ all -> 0x012b }
            if (r12 != r10) goto L_0x00d0
            return r10
        L_0x00d0:
            r14 = r12
            r12 = r0
            r0 = r14
            r15 = r9
            r9 = r1
            r1 = r15
            r16 = r6
            r6 = r3
            r3 = r11
            r11 = r16
            r17 = r8
            r8 = r7
            r7 = r17
        L_0x00e1:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x012b }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x012b }
            if (r0 == 0) goto L_0x011f
            r1.L$0 = r12     // Catch:{ all -> 0x012b }
            r1.L$1 = r11     // Catch:{ all -> 0x012b }
            r1.L$2 = r8     // Catch:{ all -> 0x012b }
            r1.L$3 = r9     // Catch:{ all -> 0x012b }
            r1.L$4 = r2     // Catch:{ all -> 0x012b }
            r1.L$5 = r7     // Catch:{ all -> 0x012b }
            r1.L$6 = r6     // Catch:{ all -> 0x012b }
            r1.L$7 = r3     // Catch:{ all -> 0x012b }
            r1.label = r4     // Catch:{ all -> 0x012b }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x012b }
            if (r0 != r10) goto L_0x0102
            return r10
        L_0x0102:
            r14 = r9
            r9 = r1
            r1 = r14
            r15 = r11
            r11 = r3
            r3 = r6
            r6 = r15
            r16 = r8
            r8 = r7
            r7 = r16
        L_0x010e:
            java.lang.Object r13 = r7.invoke(r0)     // Catch:{ all -> 0x012b }
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ all -> 0x012b }
            boolean r13 = r13.booleanValue()     // Catch:{ all -> 0x012b }
            if (r13 == 0) goto L_0x011d
            r6.add(r0)     // Catch:{ all -> 0x012b }
        L_0x011d:
            r0 = r12
            goto L_0x00b7
        L_0x011f:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x012b }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            return r11
        L_0x012b:
            r0 = move-exception
            goto L_0x0130
        L_0x012d:
            r0 = move-exception
            r2 = r18
        L_0x0130:
            r1 = r0
        L_0x0131:
            throw r1     // Catch:{ all -> 0x0132 }
        L_0x0132:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x013e:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f8 A[Catch:{ all -> 0x0177 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0110 A[Catch:{ all -> 0x0177 }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0152 A[Catch:{ all -> 0x0177 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends kotlinx.coroutines.channels.SendChannel<? super E>> java.lang.Object filterTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, C r19, kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r20, kotlin.coroutines.Continuation<? super C> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$3 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$3 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterTo$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 3
            r5 = 2
            r6 = 1
            if (r3 == 0) goto L_0x00c7
            if (r3 == r6) goto L_0x0094
            if (r3 == r5) goto L_0x0065
            if (r3 != r4) goto L_0x005d
            java.lang.Object r3 = r1.L$9
            java.lang.Object r3 = r1.L$8
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r12 = (kotlinx.coroutines.channels.SendChannel) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00c2 }
            if (r14 != 0) goto L_0x0058
            goto L_0x0156
        L_0x0058:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00c2 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00c2 }
            throw r0     // Catch:{ all -> 0x00c2 }
        L_0x005d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0065:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r12 = (kotlinx.coroutines.channels.SendChannel) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00c2 }
            if (r14 != 0) goto L_0x008f
            r15 = r10
            r10 = r2
            r2 = r9
            r9 = r15
            goto L_0x0129
        L_0x008f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00c2 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00c2 }
            throw r0     // Catch:{ all -> 0x00c2 }
        L_0x0094:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r12 = (kotlinx.coroutines.channels.SendChannel) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00c2 }
            if (r14 != 0) goto L_0x00bd
            r15 = r10
            r10 = r2
            r2 = r9
            r9 = r15
            goto L_0x0108
        L_0x00bd:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00c2 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00c2 }
            throw r0     // Catch:{ all -> 0x00c2 }
        L_0x00c2:
            r0 = move-exception
            r1 = r0
            r2 = r9
            goto L_0x017d
        L_0x00c7:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x018a
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlinx.coroutines.channels.ChannelIterator r3 = r18.iterator()     // Catch:{ all -> 0x0179 }
            r7 = r19
            r8 = r20
            r12 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r0 = r18
            r1 = r0
            r2 = r1
            r3 = r2
        L_0x00df:
            r9.L$0 = r0     // Catch:{ all -> 0x0177 }
            r9.L$1 = r7     // Catch:{ all -> 0x0177 }
            r9.L$2 = r8     // Catch:{ all -> 0x0177 }
            r9.L$3 = r1     // Catch:{ all -> 0x0177 }
            r9.L$4 = r2     // Catch:{ all -> 0x0177 }
            r9.L$5 = r12     // Catch:{ all -> 0x0177 }
            r9.L$6 = r3     // Catch:{ all -> 0x0177 }
            r9.L$7 = r11     // Catch:{ all -> 0x0177 }
            r9.label = r6     // Catch:{ all -> 0x0177 }
            java.lang.Object r13 = r11.hasNext(r9)     // Catch:{ all -> 0x0177 }
            if (r13 != r10) goto L_0x00f8
            return r10
        L_0x00f8:
            r15 = r13
            r13 = r0
            r0 = r15
            r16 = r9
            r9 = r1
            r1 = r16
            r17 = r7
            r7 = r3
            r3 = r11
            r11 = r8
            r8 = r12
            r12 = r17
        L_0x0108:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0177 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0177 }
            if (r0 == 0) goto L_0x016b
            r1.L$0 = r13     // Catch:{ all -> 0x0177 }
            r1.L$1 = r12     // Catch:{ all -> 0x0177 }
            r1.L$2 = r11     // Catch:{ all -> 0x0177 }
            r1.L$3 = r9     // Catch:{ all -> 0x0177 }
            r1.L$4 = r2     // Catch:{ all -> 0x0177 }
            r1.L$5 = r8     // Catch:{ all -> 0x0177 }
            r1.L$6 = r7     // Catch:{ all -> 0x0177 }
            r1.L$7 = r3     // Catch:{ all -> 0x0177 }
            r1.label = r5     // Catch:{ all -> 0x0177 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0177 }
            if (r0 != r10) goto L_0x0129
            return r10
        L_0x0129:
            java.lang.Object r14 = r11.invoke(r0)     // Catch:{ all -> 0x0177 }
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ all -> 0x0177 }
            boolean r14 = r14.booleanValue()     // Catch:{ all -> 0x0177 }
            if (r14 == 0) goto L_0x0166
            r1.L$0 = r13     // Catch:{ all -> 0x0177 }
            r1.L$1 = r12     // Catch:{ all -> 0x0177 }
            r1.L$2 = r11     // Catch:{ all -> 0x0177 }
            r1.L$3 = r9     // Catch:{ all -> 0x0177 }
            r1.L$4 = r2     // Catch:{ all -> 0x0177 }
            r1.L$5 = r8     // Catch:{ all -> 0x0177 }
            r1.L$6 = r7     // Catch:{ all -> 0x0177 }
            r1.L$7 = r3     // Catch:{ all -> 0x0177 }
            r1.L$8 = r0     // Catch:{ all -> 0x0177 }
            r1.L$9 = r0     // Catch:{ all -> 0x0177 }
            r1.label = r4     // Catch:{ all -> 0x0177 }
            java.lang.Object r0 = r12.send(r0, r1)     // Catch:{ all -> 0x0177 }
            if (r0 != r10) goto L_0x0152
            return r10
        L_0x0152:
            r15 = r9
            r9 = r2
            r2 = r10
            r10 = r15
        L_0x0156:
            r0 = r13
            r15 = r9
            r9 = r1
            r1 = r10
            r10 = r2
            r2 = r15
        L_0x015c:
            r16 = r11
            r11 = r3
            r3 = r7
            r7 = r12
            r12 = r8
            r8 = r16
            goto L_0x00df
        L_0x0166:
            r0 = r13
            r15 = r9
            r9 = r1
            r1 = r15
            goto L_0x015c
        L_0x016b:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0177 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            r2.cancel(r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            return r12
        L_0x0177:
            r0 = move-exception
            goto L_0x017c
        L_0x0179:
            r0 = move-exception
            r2 = r18
        L_0x017c:
            r1 = r0
        L_0x017d:
            throw r1     // Catch:{ all -> 0x017e }
        L_0x017e:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            throw r3
        L_0x018a:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ ReceiveChannel take$default(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.take(receiveChannel, i, coroutineContext);
    }

    public static final <E> ReceiveChannel<E> take(ReceiveChannel<? extends E> receiveChannel, int i, CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$take$1(receiveChannel, i, (Continuation) null), 2, (Object) null);
    }

    public static /* synthetic */ ReceiveChannel takeWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.takeWhile(receiveChannel, coroutineContext, function2);
    }

    public static final <E> ReceiveChannel<E> takeWhile(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "predicate");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$takeWhile$1(receiveChannel, function2, (Continuation) null), 2, (Object) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00dd A[Catch:{ all -> 0x0134 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00de A[Catch:{ all -> 0x0134 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f3 A[Catch:{ all -> 0x0134 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K, V> java.lang.Object associate(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r17, kotlin.jvm.functions.Function1<? super E, ? extends kotlin.Pair<? extends K, ? extends V>> r18, kotlin.coroutines.Continuation<? super java.util.Map<K, ? extends V>> r19) {
        /*
            r0 = r19
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associate$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associate$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associate$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associate$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associate$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00a4
            if (r3 == r5) goto L_0x006c
            if (r3 != r4) goto L_0x0064
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            java.util.Map r10 = (java.util.Map) r10
            java.lang.Object r11 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009f }
            if (r14 != 0) goto L_0x005f
            r15 = r9
            r9 = r1
            r1 = r11
            r11 = r7
            r7 = r12
            r12 = r3
            r3 = r8
        L_0x005a:
            r8 = r10
            r10 = r2
            r2 = r15
            goto L_0x0115
        L_0x005f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009f }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009f }
            throw r0     // Catch:{ all -> 0x009f }
        L_0x0064:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006c:
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            java.util.Map r10 = (java.util.Map) r10
            java.lang.Object r11 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009f }
            if (r14 != 0) goto L_0x009a
            r15 = r12
            r12 = r3
            r3 = r8
            r8 = r15
            goto L_0x00eb
        L_0x009a:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009f }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009f }
            throw r0     // Catch:{ all -> 0x009f }
        L_0x009f:
            r0 = move-exception
            r1 = r0
            r3 = r8
            goto L_0x013a
        L_0x00a4:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0147
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r6 = r17.iterator()     // Catch:{ all -> 0x0136 }
            r7 = r18
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r12 = r6
            r0 = r17
            r1 = r0
            r2 = r1
            r3 = r2
            r6 = r3
        L_0x00c3:
            r9.L$0 = r0     // Catch:{ all -> 0x0134 }
            r9.L$1 = r7     // Catch:{ all -> 0x0134 }
            r9.L$2 = r1     // Catch:{ all -> 0x0134 }
            r9.L$3 = r8     // Catch:{ all -> 0x0134 }
            r9.L$4 = r2     // Catch:{ all -> 0x0134 }
            r9.L$5 = r3     // Catch:{ all -> 0x0134 }
            r9.L$6 = r11     // Catch:{ all -> 0x0134 }
            r9.L$7 = r6     // Catch:{ all -> 0x0134 }
            r9.L$8 = r12     // Catch:{ all -> 0x0134 }
            r9.label = r5     // Catch:{ all -> 0x0134 }
            java.lang.Object r13 = r12.hasNext(r9)     // Catch:{ all -> 0x0134 }
            if (r13 != r10) goto L_0x00de
            return r10
        L_0x00de:
            r15 = r13
            r13 = r0
            r0 = r15
            r16 = r11
            r11 = r1
            r1 = r9
            r9 = r2
            r2 = r10
            r10 = r8
            r8 = r7
            r7 = r16
        L_0x00eb:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0134 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0134 }
            if (r0 == 0) goto L_0x0128
            r1.L$0 = r13     // Catch:{ all -> 0x0134 }
            r1.L$1 = r8     // Catch:{ all -> 0x0134 }
            r1.L$2 = r11     // Catch:{ all -> 0x0134 }
            r1.L$3 = r10     // Catch:{ all -> 0x0134 }
            r1.L$4 = r9     // Catch:{ all -> 0x0134 }
            r1.L$5 = r3     // Catch:{ all -> 0x0134 }
            r1.L$6 = r7     // Catch:{ all -> 0x0134 }
            r1.L$7 = r6     // Catch:{ all -> 0x0134 }
            r1.L$8 = r12     // Catch:{ all -> 0x0134 }
            r1.label = r4     // Catch:{ all -> 0x0134 }
            java.lang.Object r0 = r12.next(r1)     // Catch:{ all -> 0x0134 }
            if (r0 != r2) goto L_0x010e
            return r2
        L_0x010e:
            r15 = r9
            r9 = r1
            r1 = r11
            r11 = r7
            r7 = r8
            goto L_0x005a
        L_0x0115:
            java.lang.Object r0 = r7.invoke(r0)     // Catch:{ all -> 0x0134 }
            kotlin.Pair r0 = (kotlin.Pair) r0     // Catch:{ all -> 0x0134 }
            java.lang.Object r14 = r0.getFirst()     // Catch:{ all -> 0x0134 }
            java.lang.Object r0 = r0.getSecond()     // Catch:{ all -> 0x0134 }
            r8.put(r14, r0)     // Catch:{ all -> 0x0134 }
            r0 = r13
            goto L_0x00c3
        L_0x0128:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0134 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r3.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            return r10
        L_0x0134:
            r0 = move-exception
            goto L_0x0139
        L_0x0136:
            r0 = move-exception
            r3 = r17
        L_0x0139:
            r1 = r0
        L_0x013a:
            throw r1     // Catch:{ all -> 0x013b }
        L_0x013b:
            r0 = move-exception
            r2 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r3.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r2
        L_0x0147:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associate(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004c, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004d, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r6.cancel(r7);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0056, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object associate$$forInline(kotlinx.coroutines.channels.ReceiveChannel r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            r1 = 0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = 1
            kotlinx.coroutines.channels.ChannelIterator r3 = r6.iterator()     // Catch:{ all -> 0x004a }
        L_0x000f:
            r4 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x004a }
            java.lang.Object r5 = r3.hasNext(r8)     // Catch:{ all -> 0x004a }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x004a }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x004a }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x004a }
            if (r5 == 0) goto L_0x003e
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x004a }
            java.lang.Object r4 = r3.next(r8)     // Catch:{ all -> 0x004a }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x004a }
            java.lang.Object r4 = r7.invoke(r4)     // Catch:{ all -> 0x004a }
            kotlin.Pair r4 = (kotlin.Pair) r4     // Catch:{ all -> 0x004a }
            java.lang.Object r5 = r4.getFirst()     // Catch:{ all -> 0x004a }
            java.lang.Object r4 = r4.getSecond()     // Catch:{ all -> 0x004a }
            r0.put(r5, r4)     // Catch:{ all -> 0x004a }
            goto L_0x000f
        L_0x003e:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x004a }
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r6.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            return r0
        L_0x004a:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x004c }
        L_0x004c:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r6.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associate$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00dd A[Catch:{ all -> 0x012a }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00de A[Catch:{ all -> 0x012a }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f3 A[Catch:{ all -> 0x012a }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K> java.lang.Object associateBy(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r17, kotlin.jvm.functions.Function1<? super E, ? extends K> r18, kotlin.coroutines.Continuation<? super java.util.Map<K, ? extends E>> r19) {
        /*
            r0 = r19
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00a4
            if (r3 == r5) goto L_0x006c
            if (r3 != r4) goto L_0x0064
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            java.util.Map r10 = (java.util.Map) r10
            java.lang.Object r11 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009f }
            if (r14 != 0) goto L_0x005f
            r15 = r9
            r9 = r1
            r1 = r11
            r11 = r7
            r7 = r12
            r12 = r3
            r3 = r8
        L_0x005a:
            r8 = r10
            r10 = r2
            r2 = r15
            goto L_0x0115
        L_0x005f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009f }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009f }
            throw r0     // Catch:{ all -> 0x009f }
        L_0x0064:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006c:
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            java.util.Map r10 = (java.util.Map) r10
            java.lang.Object r11 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009f }
            if (r14 != 0) goto L_0x009a
            r15 = r12
            r12 = r3
            r3 = r8
            r8 = r15
            goto L_0x00eb
        L_0x009a:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009f }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009f }
            throw r0     // Catch:{ all -> 0x009f }
        L_0x009f:
            r0 = move-exception
            r1 = r0
            r3 = r8
            goto L_0x0130
        L_0x00a4:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x013d
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r6 = r17.iterator()     // Catch:{ all -> 0x012c }
            r7 = r18
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r12 = r6
            r0 = r17
            r1 = r0
            r2 = r1
            r3 = r2
            r6 = r3
        L_0x00c3:
            r9.L$0 = r0     // Catch:{ all -> 0x012a }
            r9.L$1 = r7     // Catch:{ all -> 0x012a }
            r9.L$2 = r1     // Catch:{ all -> 0x012a }
            r9.L$3 = r8     // Catch:{ all -> 0x012a }
            r9.L$4 = r2     // Catch:{ all -> 0x012a }
            r9.L$5 = r3     // Catch:{ all -> 0x012a }
            r9.L$6 = r11     // Catch:{ all -> 0x012a }
            r9.L$7 = r6     // Catch:{ all -> 0x012a }
            r9.L$8 = r12     // Catch:{ all -> 0x012a }
            r9.label = r5     // Catch:{ all -> 0x012a }
            java.lang.Object r13 = r12.hasNext(r9)     // Catch:{ all -> 0x012a }
            if (r13 != r10) goto L_0x00de
            return r10
        L_0x00de:
            r15 = r13
            r13 = r0
            r0 = r15
            r16 = r11
            r11 = r1
            r1 = r9
            r9 = r2
            r2 = r10
            r10 = r8
            r8 = r7
            r7 = r16
        L_0x00eb:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x012a }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x012a }
            if (r0 == 0) goto L_0x011e
            r1.L$0 = r13     // Catch:{ all -> 0x012a }
            r1.L$1 = r8     // Catch:{ all -> 0x012a }
            r1.L$2 = r11     // Catch:{ all -> 0x012a }
            r1.L$3 = r10     // Catch:{ all -> 0x012a }
            r1.L$4 = r9     // Catch:{ all -> 0x012a }
            r1.L$5 = r3     // Catch:{ all -> 0x012a }
            r1.L$6 = r7     // Catch:{ all -> 0x012a }
            r1.L$7 = r6     // Catch:{ all -> 0x012a }
            r1.L$8 = r12     // Catch:{ all -> 0x012a }
            r1.label = r4     // Catch:{ all -> 0x012a }
            java.lang.Object r0 = r12.next(r1)     // Catch:{ all -> 0x012a }
            if (r0 != r2) goto L_0x010e
            return r2
        L_0x010e:
            r15 = r9
            r9 = r1
            r1 = r11
            r11 = r7
            r7 = r8
            goto L_0x005a
        L_0x0115:
            java.lang.Object r14 = r7.invoke(r0)     // Catch:{ all -> 0x012a }
            r8.put(r14, r0)     // Catch:{ all -> 0x012a }
            r0 = r13
            goto L_0x00c3
        L_0x011e:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x012a }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r3.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            return r10
        L_0x012a:
            r0 = move-exception
            goto L_0x012f
        L_0x012c:
            r0 = move-exception
            r3 = r17
        L_0x012f:
            r1 = r0
        L_0x0130:
            throw r1     // Catch:{ all -> 0x0131 }
        L_0x0131:
            r0 = move-exception
            r2 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r3.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r2
        L_0x013d:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0042, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0043, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r6.cancel(r7);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004c, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object associateBy$$forInline(kotlinx.coroutines.channels.ReceiveChannel r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            r1 = 0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = 1
            kotlinx.coroutines.channels.ChannelIterator r3 = r6.iterator()     // Catch:{ all -> 0x0040 }
        L_0x000f:
            r4 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0040 }
            java.lang.Object r5 = r3.hasNext(r8)     // Catch:{ all -> 0x0040 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0040 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0040 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0040 }
            if (r5 == 0) goto L_0x0034
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0040 }
            java.lang.Object r4 = r3.next(r8)     // Catch:{ all -> 0x0040 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0040 }
            java.lang.Object r5 = r7.invoke(r4)     // Catch:{ all -> 0x0040 }
            r0.put(r5, r4)     // Catch:{ all -> 0x0040 }
            goto L_0x000f
        L_0x0034:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0040 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r6.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            return r0
        L_0x0040:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0042 }
        L_0x0042:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r6.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateBy$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f2 A[Catch:{ all -> 0x0151 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x010d A[Catch:{ all -> 0x0151 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K, V> java.lang.Object associateBy(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r19, kotlin.jvm.functions.Function1<? super E, ? extends K> r20, kotlin.jvm.functions.Function1<? super E, ? extends V> r21, kotlin.coroutines.Continuation<? super java.util.Map<K, ? extends V>> r22) {
        /*
            r0 = r22
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$2
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$2 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$2) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$2 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateBy$2
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00b4
            if (r3 == r5) goto L_0x0076
            if (r3 != r4) goto L_0x006e
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$7
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            java.util.Map r10 = (java.util.Map) r10
            java.lang.Object r11 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$1
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00af }
            if (r15 != 0) goto L_0x0069
            r16 = r10
            r10 = r1
            r1 = r11
            r11 = r2
            r2 = r9
            r9 = r16
            r17 = r13
            r13 = r3
            r3 = r8
            r8 = r12
            r12 = r7
            r7 = r17
            goto L_0x0138
        L_0x0069:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00af }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00af }
            throw r0     // Catch:{ all -> 0x00af }
        L_0x006e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0076:
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$7
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            java.util.Map r10 = (java.util.Map) r10
            java.lang.Object r11 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$1
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00af }
            if (r15 != 0) goto L_0x00aa
            r16 = r13
            r13 = r3
            r3 = r8
            r8 = r16
            goto L_0x0105
        L_0x00aa:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00af }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00af }
            throw r0     // Catch:{ all -> 0x00af }
        L_0x00af:
            r0 = move-exception
            r1 = r0
            r3 = r8
            goto L_0x0157
        L_0x00b4:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0164
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r6 = r19.iterator()     // Catch:{ all -> 0x0153 }
            r7 = r20
            r8 = r21
            r9 = r0
            r10 = r1
            r11 = r2
            r12 = r3
            r13 = r6
            r0 = r19
            r1 = r0
            r2 = r1
            r3 = r2
            r6 = r3
        L_0x00d5:
            r10.L$0 = r0     // Catch:{ all -> 0x0151 }
            r10.L$1 = r7     // Catch:{ all -> 0x0151 }
            r10.L$2 = r8     // Catch:{ all -> 0x0151 }
            r10.L$3 = r1     // Catch:{ all -> 0x0151 }
            r10.L$4 = r9     // Catch:{ all -> 0x0151 }
            r10.L$5 = r2     // Catch:{ all -> 0x0151 }
            r10.L$6 = r3     // Catch:{ all -> 0x0151 }
            r10.L$7 = r12     // Catch:{ all -> 0x0151 }
            r10.L$8 = r6     // Catch:{ all -> 0x0151 }
            r10.L$9 = r13     // Catch:{ all -> 0x0151 }
            r10.label = r5     // Catch:{ all -> 0x0151 }
            java.lang.Object r14 = r13.hasNext(r10)     // Catch:{ all -> 0x0151 }
            if (r14 != r11) goto L_0x00f2
            return r11
        L_0x00f2:
            r16 = r14
            r14 = r0
            r0 = r16
            r17 = r11
            r11 = r1
            r1 = r10
            r10 = r9
            r9 = r2
            r2 = r17
            r18 = r8
            r8 = r7
            r7 = r12
            r12 = r18
        L_0x0105:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0151 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0151 }
            if (r0 == 0) goto L_0x0145
            r1.L$0 = r14     // Catch:{ all -> 0x0151 }
            r1.L$1 = r8     // Catch:{ all -> 0x0151 }
            r1.L$2 = r12     // Catch:{ all -> 0x0151 }
            r1.L$3 = r11     // Catch:{ all -> 0x0151 }
            r1.L$4 = r10     // Catch:{ all -> 0x0151 }
            r1.L$5 = r9     // Catch:{ all -> 0x0151 }
            r1.L$6 = r3     // Catch:{ all -> 0x0151 }
            r1.L$7 = r7     // Catch:{ all -> 0x0151 }
            r1.L$8 = r6     // Catch:{ all -> 0x0151 }
            r1.L$9 = r13     // Catch:{ all -> 0x0151 }
            r1.label = r4     // Catch:{ all -> 0x0151 }
            java.lang.Object r0 = r13.next(r1)     // Catch:{ all -> 0x0151 }
            if (r0 != r2) goto L_0x012a
            return r2
        L_0x012a:
            r16 = r10
            r10 = r1
            r1 = r11
            r11 = r2
            r2 = r9
            r9 = r16
            r17 = r12
            r12 = r7
            r7 = r8
            r8 = r17
        L_0x0138:
            java.lang.Object r15 = r7.invoke(r0)     // Catch:{ all -> 0x0151 }
            java.lang.Object r0 = r8.invoke(r0)     // Catch:{ all -> 0x0151 }
            r9.put(r15, r0)     // Catch:{ all -> 0x0151 }
            r0 = r14
            goto L_0x00d5
        L_0x0145:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0151 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r3.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            return r10
        L_0x0151:
            r0 = move-exception
            goto L_0x0156
        L_0x0153:
            r0 = move-exception
            r3 = r19
        L_0x0156:
            r1 = r0
        L_0x0157:
            throw r1     // Catch:{ all -> 0x0158 }
        L_0x0158:
            r0 = move-exception
            r2 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r3.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r2
        L_0x0164:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0046, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0047, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r6.cancel(r7);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0050, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object associateBy$$forInline(kotlinx.coroutines.channels.ReceiveChannel r6, kotlin.jvm.functions.Function1 r7, kotlin.jvm.functions.Function1 r8, kotlin.coroutines.Continuation r9) {
        /*
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            r1 = 0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = 1
            kotlinx.coroutines.channels.ChannelIterator r3 = r6.iterator()     // Catch:{ all -> 0x0044 }
        L_0x000f:
            r4 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0044 }
            java.lang.Object r5 = r3.hasNext(r9)     // Catch:{ all -> 0x0044 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0044 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0044 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0044 }
            if (r5 == 0) goto L_0x0038
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0044 }
            java.lang.Object r4 = r3.next(r9)     // Catch:{ all -> 0x0044 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0044 }
            java.lang.Object r5 = r7.invoke(r4)     // Catch:{ all -> 0x0044 }
            java.lang.Object r4 = r8.invoke(r4)     // Catch:{ all -> 0x0044 }
            r0.put(r5, r4)     // Catch:{ all -> 0x0044 }
            goto L_0x000f
        L_0x0038:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0044 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r6.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            return r0
        L_0x0044:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0046 }
        L_0x0046:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r6.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateBy$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d0 A[Catch:{ all -> 0x0123 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e9 A[Catch:{ all -> 0x0123 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K, M extends java.util.Map<? super K, ? super E>> java.lang.Object associateByTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, M r19, kotlin.jvm.functions.Function1<? super E, ? extends K> r20, kotlin.coroutines.Continuation<? super M> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x009f
            if (r3 == r5) goto L_0x006c
            if (r3 != r4) goto L_0x0064
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$1
            java.util.Map r11 = (java.util.Map) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009a }
            if (r13 != 0) goto L_0x005f
            r14 = r9
            r9 = r1
            r1 = r14
            r15 = r10
            r10 = r2
            r2 = r8
            r8 = r7
            r7 = r15
            r16 = r11
            r11 = r3
            r3 = r6
            r6 = r16
            goto L_0x010e
        L_0x005f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009a }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009a }
            throw r0     // Catch:{ all -> 0x009a }
        L_0x0064:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006c:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$1
            java.util.Map r11 = (java.util.Map) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009a }
            if (r13 != 0) goto L_0x0095
            r14 = r10
            r10 = r2
            r2 = r8
            r8 = r14
            goto L_0x00e1
        L_0x0095:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009a }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009a }
            throw r0     // Catch:{ all -> 0x009a }
        L_0x009a:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x0129
        L_0x009f:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0136
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlinx.coroutines.channels.ChannelIterator r3 = r18.iterator()     // Catch:{ all -> 0x0125 }
            r6 = r19
            r7 = r20
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r0 = r18
            r1 = r0
            r2 = r1
            r3 = r2
        L_0x00b7:
            r9.L$0 = r0     // Catch:{ all -> 0x0123 }
            r9.L$1 = r6     // Catch:{ all -> 0x0123 }
            r9.L$2 = r7     // Catch:{ all -> 0x0123 }
            r9.L$3 = r1     // Catch:{ all -> 0x0123 }
            r9.L$4 = r2     // Catch:{ all -> 0x0123 }
            r9.L$5 = r8     // Catch:{ all -> 0x0123 }
            r9.L$6 = r3     // Catch:{ all -> 0x0123 }
            r9.L$7 = r11     // Catch:{ all -> 0x0123 }
            r9.label = r5     // Catch:{ all -> 0x0123 }
            java.lang.Object r12 = r11.hasNext(r9)     // Catch:{ all -> 0x0123 }
            if (r12 != r10) goto L_0x00d0
            return r10
        L_0x00d0:
            r14 = r12
            r12 = r0
            r0 = r14
            r15 = r9
            r9 = r1
            r1 = r15
            r16 = r6
            r6 = r3
            r3 = r11
            r11 = r16
            r17 = r8
            r8 = r7
            r7 = r17
        L_0x00e1:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0123 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0123 }
            if (r0 == 0) goto L_0x0117
            r1.L$0 = r12     // Catch:{ all -> 0x0123 }
            r1.L$1 = r11     // Catch:{ all -> 0x0123 }
            r1.L$2 = r8     // Catch:{ all -> 0x0123 }
            r1.L$3 = r9     // Catch:{ all -> 0x0123 }
            r1.L$4 = r2     // Catch:{ all -> 0x0123 }
            r1.L$5 = r7     // Catch:{ all -> 0x0123 }
            r1.L$6 = r6     // Catch:{ all -> 0x0123 }
            r1.L$7 = r3     // Catch:{ all -> 0x0123 }
            r1.label = r4     // Catch:{ all -> 0x0123 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0123 }
            if (r0 != r10) goto L_0x0102
            return r10
        L_0x0102:
            r14 = r9
            r9 = r1
            r1 = r14
            r15 = r11
            r11 = r3
            r3 = r6
            r6 = r15
            r16 = r8
            r8 = r7
            r7 = r16
        L_0x010e:
            java.lang.Object r13 = r7.invoke(r0)     // Catch:{ all -> 0x0123 }
            r6.put(r13, r0)     // Catch:{ all -> 0x0123 }
            r0 = r12
            goto L_0x00b7
        L_0x0117:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0123 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            return r11
        L_0x0123:
            r0 = move-exception
            goto L_0x0128
        L_0x0125:
            r0 = move-exception
            r2 = r18
        L_0x0128:
            r1 = r0
        L_0x0129:
            throw r1     // Catch:{ all -> 0x012a }
        L_0x012a:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x0136:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateByTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00dc A[Catch:{ all -> 0x0131 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00dd A[Catch:{ all -> 0x0131 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f6 A[Catch:{ all -> 0x0131 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K, V, M extends java.util.Map<? super K, ? super V>> java.lang.Object associateByTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, M r19, kotlin.jvm.functions.Function1<? super E, ? extends K> r20, kotlin.jvm.functions.Function1<? super E, ? extends V> r21, kotlin.coroutines.Continuation<? super M> r22) {
        /*
            r0 = r22
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$3 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$3 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateByTo$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00a8
            if (r3 == r5) goto L_0x0070
            if (r3 != r4) goto L_0x0068
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$1
            java.util.Map r12 = (java.util.Map) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a3 }
            if (r14 != 0) goto L_0x0063
            r15 = r10
            r10 = r1
            r1 = r9
            r9 = r7
            r7 = r11
            r11 = r2
            r2 = r8
        L_0x005a:
            r8 = r15
            r16 = r12
            r12 = r3
            r3 = r6
            r6 = r16
            goto L_0x0118
        L_0x0063:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a3 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a3 }
            throw r0     // Catch:{ all -> 0x00a3 }
        L_0x0068:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0070:
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$1
            java.util.Map r12 = (java.util.Map) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a3 }
            if (r14 != 0) goto L_0x009e
            r15 = r11
            r11 = r2
            r2 = r8
            r8 = r15
            goto L_0x00ee
        L_0x009e:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a3 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a3 }
            throw r0     // Catch:{ all -> 0x00a3 }
        L_0x00a3:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x0137
        L_0x00a8:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0144
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlinx.coroutines.channels.ChannelIterator r3 = r18.iterator()     // Catch:{ all -> 0x0133 }
            r6 = r19
            r7 = r20
            r8 = r21
            r9 = r0
            r10 = r1
            r11 = r2
            r12 = r3
            r0 = r18
            r1 = r0
            r2 = r1
            r3 = r2
        L_0x00c2:
            r10.L$0 = r0     // Catch:{ all -> 0x0131 }
            r10.L$1 = r6     // Catch:{ all -> 0x0131 }
            r10.L$2 = r7     // Catch:{ all -> 0x0131 }
            r10.L$3 = r8     // Catch:{ all -> 0x0131 }
            r10.L$4 = r1     // Catch:{ all -> 0x0131 }
            r10.L$5 = r2     // Catch:{ all -> 0x0131 }
            r10.L$6 = r9     // Catch:{ all -> 0x0131 }
            r10.L$7 = r3     // Catch:{ all -> 0x0131 }
            r10.L$8 = r12     // Catch:{ all -> 0x0131 }
            r10.label = r5     // Catch:{ all -> 0x0131 }
            java.lang.Object r13 = r12.hasNext(r10)     // Catch:{ all -> 0x0131 }
            if (r13 != r11) goto L_0x00dd
            return r11
        L_0x00dd:
            r15 = r13
            r13 = r0
            r0 = r15
            r16 = r9
            r9 = r1
            r1 = r10
            r10 = r8
            r8 = r7
            r7 = r16
            r17 = r6
            r6 = r3
            r3 = r12
            r12 = r17
        L_0x00ee:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0131 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0131 }
            if (r0 == 0) goto L_0x0125
            r1.L$0 = r13     // Catch:{ all -> 0x0131 }
            r1.L$1 = r12     // Catch:{ all -> 0x0131 }
            r1.L$2 = r8     // Catch:{ all -> 0x0131 }
            r1.L$3 = r10     // Catch:{ all -> 0x0131 }
            r1.L$4 = r9     // Catch:{ all -> 0x0131 }
            r1.L$5 = r2     // Catch:{ all -> 0x0131 }
            r1.L$6 = r7     // Catch:{ all -> 0x0131 }
            r1.L$7 = r6     // Catch:{ all -> 0x0131 }
            r1.L$8 = r3     // Catch:{ all -> 0x0131 }
            r1.label = r4     // Catch:{ all -> 0x0131 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0131 }
            if (r0 != r11) goto L_0x0111
            return r11
        L_0x0111:
            r15 = r10
            r10 = r1
            r1 = r9
            r9 = r7
            r7 = r8
            goto L_0x005a
        L_0x0118:
            java.lang.Object r14 = r7.invoke(r0)     // Catch:{ all -> 0x0131 }
            java.lang.Object r0 = r8.invoke(r0)     // Catch:{ all -> 0x0131 }
            r6.put(r14, r0)     // Catch:{ all -> 0x0131 }
            r0 = r13
            goto L_0x00c2
        L_0x0125:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0131 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            return r12
        L_0x0131:
            r0 = move-exception
            goto L_0x0136
        L_0x0133:
            r0 = move-exception
            r2 = r18
        L_0x0136:
            r1 = r0
        L_0x0137:
            throw r1     // Catch:{ all -> 0x0138 }
        L_0x0138:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x0144:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateByTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d0 A[Catch:{ all -> 0x012d }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e9 A[Catch:{ all -> 0x012d }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K, V, M extends java.util.Map<? super K, ? super V>> java.lang.Object associateTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, M r19, kotlin.jvm.functions.Function1<? super E, ? extends kotlin.Pair<? extends K, ? extends V>> r20, kotlin.coroutines.Continuation<? super M> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateTo$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateTo$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateTo$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateTo$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$associateTo$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x009f
            if (r3 == r5) goto L_0x006c
            if (r3 != r4) goto L_0x0064
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$1
            java.util.Map r11 = (java.util.Map) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009a }
            if (r13 != 0) goto L_0x005f
            r14 = r9
            r9 = r1
            r1 = r14
            r15 = r10
            r10 = r2
            r2 = r8
            r8 = r7
            r7 = r15
            r16 = r11
            r11 = r3
            r3 = r6
            r6 = r16
            goto L_0x010e
        L_0x005f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009a }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009a }
            throw r0     // Catch:{ all -> 0x009a }
        L_0x0064:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006c:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$1
            java.util.Map r11 = (java.util.Map) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009a }
            if (r13 != 0) goto L_0x0095
            r14 = r10
            r10 = r2
            r2 = r8
            r8 = r14
            goto L_0x00e1
        L_0x0095:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009a }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009a }
            throw r0     // Catch:{ all -> 0x009a }
        L_0x009a:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x0133
        L_0x009f:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0140
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlinx.coroutines.channels.ChannelIterator r3 = r18.iterator()     // Catch:{ all -> 0x012f }
            r6 = r19
            r7 = r20
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r0 = r18
            r1 = r0
            r2 = r1
            r3 = r2
        L_0x00b7:
            r9.L$0 = r0     // Catch:{ all -> 0x012d }
            r9.L$1 = r6     // Catch:{ all -> 0x012d }
            r9.L$2 = r7     // Catch:{ all -> 0x012d }
            r9.L$3 = r1     // Catch:{ all -> 0x012d }
            r9.L$4 = r2     // Catch:{ all -> 0x012d }
            r9.L$5 = r8     // Catch:{ all -> 0x012d }
            r9.L$6 = r3     // Catch:{ all -> 0x012d }
            r9.L$7 = r11     // Catch:{ all -> 0x012d }
            r9.label = r5     // Catch:{ all -> 0x012d }
            java.lang.Object r12 = r11.hasNext(r9)     // Catch:{ all -> 0x012d }
            if (r12 != r10) goto L_0x00d0
            return r10
        L_0x00d0:
            r14 = r12
            r12 = r0
            r0 = r14
            r15 = r9
            r9 = r1
            r1 = r15
            r16 = r6
            r6 = r3
            r3 = r11
            r11 = r16
            r17 = r8
            r8 = r7
            r7 = r17
        L_0x00e1:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x012d }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x012d }
            if (r0 == 0) goto L_0x0121
            r1.L$0 = r12     // Catch:{ all -> 0x012d }
            r1.L$1 = r11     // Catch:{ all -> 0x012d }
            r1.L$2 = r8     // Catch:{ all -> 0x012d }
            r1.L$3 = r9     // Catch:{ all -> 0x012d }
            r1.L$4 = r2     // Catch:{ all -> 0x012d }
            r1.L$5 = r7     // Catch:{ all -> 0x012d }
            r1.L$6 = r6     // Catch:{ all -> 0x012d }
            r1.L$7 = r3     // Catch:{ all -> 0x012d }
            r1.label = r4     // Catch:{ all -> 0x012d }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x012d }
            if (r0 != r10) goto L_0x0102
            return r10
        L_0x0102:
            r14 = r9
            r9 = r1
            r1 = r14
            r15 = r11
            r11 = r3
            r3 = r6
            r6 = r15
            r16 = r8
            r8 = r7
            r7 = r16
        L_0x010e:
            java.lang.Object r0 = r7.invoke(r0)     // Catch:{ all -> 0x012d }
            kotlin.Pair r0 = (kotlin.Pair) r0     // Catch:{ all -> 0x012d }
            java.lang.Object r13 = r0.getFirst()     // Catch:{ all -> 0x012d }
            java.lang.Object r0 = r0.getSecond()     // Catch:{ all -> 0x012d }
            r6.put(r13, r0)     // Catch:{ all -> 0x012d }
            r0 = r12
            goto L_0x00b7
        L_0x0121:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x012d }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            return r11
        L_0x012d:
            r0 = move-exception
            goto L_0x0132
        L_0x012f:
            r0 = move-exception
            r2 = r18
        L_0x0132:
            r1 = r0
        L_0x0133:
            throw r1     // Catch:{ all -> 0x0134 }
        L_0x0134:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x0140:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ed A[Catch:{ all -> 0x0145 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ee A[Catch:{ all -> 0x0145 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ff A[Catch:{ all -> 0x0145 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends kotlinx.coroutines.channels.SendChannel<? super E>> java.lang.Object toChannel(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, C r14, kotlin.coroutines.Continuation<? super C> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toChannel$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toChannel$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toChannel$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toChannel$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toChannel$1
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x00c3
            if (r2 == r5) goto L_0x0096
            if (r2 == r4) goto L_0x0069
            if (r2 != r3) goto L_0x0061
            java.lang.Object r13 = r0.L$8
            java.lang.Object r13 = r0.L$7
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.channels.SendChannel r8 = (kotlinx.coroutines.channels.SendChannel) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            boolean r10 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00bf }
            if (r10 != 0) goto L_0x005c
            r15 = r7
            r7 = r1
            r1 = r14
            r14 = r9
            r11 = r6
            r6 = r0
            r0 = r11
        L_0x0057:
            r12 = r8
            r8 = r2
            r2 = r12
            goto L_0x00d7
        L_0x005c:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x00bf }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x00bf }
            throw r13     // Catch:{ all -> 0x00bf }
        L_0x0061:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0069:
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.channels.SendChannel r8 = (kotlinx.coroutines.channels.SendChannel) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            boolean r10 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00bf }
            if (r10 != 0) goto L_0x0091
            r11 = r14
            r14 = r13
            r13 = r6
            r6 = r0
        L_0x008d:
            r0 = r15
            r15 = r11
            goto L_0x011b
        L_0x0091:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x00bf }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x00bf }
            throw r13     // Catch:{ all -> 0x00bf }
        L_0x0096:
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.channels.SendChannel r8 = (kotlinx.coroutines.channels.SendChannel) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            boolean r10 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00bf }
            if (r10 != 0) goto L_0x00ba
            r11 = r6
            r6 = r0
            r0 = r11
            goto L_0x00f7
        L_0x00ba:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x00bf }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x00bf }
            throw r13     // Catch:{ all -> 0x00bf }
        L_0x00bf:
            r13 = move-exception
            r0 = r6
            goto L_0x014a
        L_0x00c3:
            boolean r2 = r15 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x0150
            r15 = 0
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            kotlinx.coroutines.channels.ChannelIterator r2 = r13.iterator()     // Catch:{ all -> 0x0147 }
            r8 = r15
            r6 = r0
            r7 = r1
            r15 = r13
            r0 = r15
            r1 = r0
            r13 = r2
            r2 = r14
            r14 = r1
        L_0x00d7:
            r6.L$0 = r14     // Catch:{ all -> 0x0145 }
            r6.L$1 = r2     // Catch:{ all -> 0x0145 }
            r6.L$2 = r15     // Catch:{ all -> 0x0145 }
            r6.L$3 = r0     // Catch:{ all -> 0x0145 }
            r6.L$4 = r8     // Catch:{ all -> 0x0145 }
            r6.L$5 = r1     // Catch:{ all -> 0x0145 }
            r6.L$6 = r13     // Catch:{ all -> 0x0145 }
            r6.label = r5     // Catch:{ all -> 0x0145 }
            java.lang.Object r9 = r13.hasNext(r6)     // Catch:{ all -> 0x0145 }
            if (r9 != r7) goto L_0x00ee
            return r7
        L_0x00ee:
            r11 = r9
            r9 = r14
            r14 = r1
            r1 = r7
            r7 = r15
            r15 = r11
            r12 = r8
            r8 = r2
            r2 = r12
        L_0x00f7:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ all -> 0x0145 }
            boolean r15 = r15.booleanValue()     // Catch:{ all -> 0x0145 }
            if (r15 == 0) goto L_0x013f
            r6.L$0 = r9     // Catch:{ all -> 0x0145 }
            r6.L$1 = r8     // Catch:{ all -> 0x0145 }
            r6.L$2 = r7     // Catch:{ all -> 0x0145 }
            r6.L$3 = r0     // Catch:{ all -> 0x0145 }
            r6.L$4 = r2     // Catch:{ all -> 0x0145 }
            r6.L$5 = r14     // Catch:{ all -> 0x0145 }
            r6.L$6 = r13     // Catch:{ all -> 0x0145 }
            r6.label = r4     // Catch:{ all -> 0x0145 }
            java.lang.Object r15 = r13.next(r6)     // Catch:{ all -> 0x0145 }
            if (r15 != r1) goto L_0x0116
            return r1
        L_0x0116:
            r11 = r14
            r14 = r13
            r13 = r0
            goto L_0x008d
        L_0x011b:
            r6.L$0 = r9     // Catch:{ all -> 0x0147 }
            r6.L$1 = r8     // Catch:{ all -> 0x0147 }
            r6.L$2 = r7     // Catch:{ all -> 0x0147 }
            r6.L$3 = r13     // Catch:{ all -> 0x0147 }
            r6.L$4 = r2     // Catch:{ all -> 0x0147 }
            r6.L$5 = r15     // Catch:{ all -> 0x0147 }
            r6.L$6 = r14     // Catch:{ all -> 0x0147 }
            r6.L$7 = r0     // Catch:{ all -> 0x0147 }
            r6.L$8 = r0     // Catch:{ all -> 0x0147 }
            r6.label = r3     // Catch:{ all -> 0x0147 }
            java.lang.Object r0 = r8.send(r0, r6)     // Catch:{ all -> 0x0147 }
            if (r0 != r1) goto L_0x0136
            return r1
        L_0x0136:
            r0 = r13
            r13 = r14
            r14 = r9
            r11 = r1
            r1 = r15
            r15 = r7
            r7 = r11
            goto L_0x0057
        L_0x013f:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0145 }
            r0.cancel(r2)
            return r8
        L_0x0145:
            r13 = move-exception
            goto L_0x014a
        L_0x0147:
            r14 = move-exception
            r0 = r13
            r13 = r14
        L_0x014a:
            throw r13     // Catch:{ all -> 0x014b }
        L_0x014b:
            r14 = move-exception
            r0.cancel(r13)
            throw r14
        L_0x0150:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15
            java.lang.Throwable r13 = r15.exception
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.toChannel(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b9 A[Catch:{ all -> 0x00f6 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ba A[Catch:{ all -> 0x00f6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ca A[Catch:{ all -> 0x00f6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, C extends java.util.Collection<? super E>> java.lang.Object toCollection(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, C r14, kotlin.coroutines.Continuation<? super C> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toCollection$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toCollection$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toCollection$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toCollection$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toCollection$1
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0090
            if (r2 == r4) goto L_0x0062
            if (r2 != r3) goto L_0x005a
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            java.util.Collection r7 = (java.util.Collection) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008c }
            if (r9 != 0) goto L_0x0055
            r10 = r8
            r8 = r13
            r13 = r10
        L_0x004c:
            r11 = r0
            r0 = r14
            r14 = r6
            r6 = r11
            r12 = r7
            r7 = r1
            r1 = r12
            goto L_0x00e8
        L_0x0055:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x008c }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x008c }
            throw r13     // Catch:{ all -> 0x008c }
        L_0x005a:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0062:
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            java.util.Collection r7 = (java.util.Collection) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008c }
            if (r9 != 0) goto L_0x0087
            r10 = r8
            r8 = r15
            r15 = r5
            r5 = r10
            goto L_0x00c2
        L_0x0087:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x008c }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x008c }
            throw r13     // Catch:{ all -> 0x008c }
        L_0x008c:
            r13 = move-exception
            r15 = r5
            goto L_0x00fb
        L_0x0090:
            boolean r2 = r15 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x0101
            r15 = 0
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            kotlinx.coroutines.channels.ChannelIterator r2 = r13.iterator()     // Catch:{ all -> 0x00f8 }
            r5 = r0
            r6 = r1
            r7 = r2
            r0 = r13
            r1 = r14
            r2 = r15
            r14 = r0
            r15 = r14
        L_0x00a3:
            r5.L$0 = r13     // Catch:{ all -> 0x00f6 }
            r5.L$1 = r1     // Catch:{ all -> 0x00f6 }
            r5.L$2 = r14     // Catch:{ all -> 0x00f6 }
            r5.L$3 = r15     // Catch:{ all -> 0x00f6 }
            r5.L$4 = r2     // Catch:{ all -> 0x00f6 }
            r5.L$5 = r0     // Catch:{ all -> 0x00f6 }
            r5.L$6 = r7     // Catch:{ all -> 0x00f6 }
            r5.label = r4     // Catch:{ all -> 0x00f6 }
            java.lang.Object r8 = r7.hasNext(r5)     // Catch:{ all -> 0x00f6 }
            if (r8 != r6) goto L_0x00ba
            return r6
        L_0x00ba:
            r10 = r5
            r5 = r13
            r13 = r7
            r7 = r1
            r1 = r6
            r6 = r14
            r14 = r0
            r0 = r10
        L_0x00c2:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x00f6 }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x00f6 }
            if (r8 == 0) goto L_0x00f0
            r0.L$0 = r5     // Catch:{ all -> 0x00f6 }
            r0.L$1 = r7     // Catch:{ all -> 0x00f6 }
            r0.L$2 = r6     // Catch:{ all -> 0x00f6 }
            r0.L$3 = r15     // Catch:{ all -> 0x00f6 }
            r0.L$4 = r2     // Catch:{ all -> 0x00f6 }
            r0.L$5 = r14     // Catch:{ all -> 0x00f6 }
            r0.L$6 = r13     // Catch:{ all -> 0x00f6 }
            r0.label = r3     // Catch:{ all -> 0x00f6 }
            java.lang.Object r8 = r13.next(r0)     // Catch:{ all -> 0x00f6 }
            if (r8 != r1) goto L_0x00e1
            return r1
        L_0x00e1:
            r10 = r8
            r8 = r13
            r13 = r5
            r5 = r15
            r15 = r10
            goto L_0x004c
        L_0x00e8:
            r1.add(r15)     // Catch:{ all -> 0x008c }
            r15 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            goto L_0x00a3
        L_0x00f0:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00f6 }
            r15.cancel(r2)
            return r7
        L_0x00f6:
            r13 = move-exception
            goto L_0x00fb
        L_0x00f8:
            r14 = move-exception
            r15 = r13
            r13 = r14
        L_0x00fb:
            throw r13     // Catch:{ all -> 0x00fc }
        L_0x00fc:
            r14 = move-exception
            r15.cancel(r13)
            throw r14
        L_0x0101:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15
            java.lang.Throwable r13 = r15.exception
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.toCollection(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final <E> Object toList(ReceiveChannel<? extends E> receiveChannel, Continuation<? super List<? extends E>> continuation) {
        return ChannelsKt.toMutableList(receiveChannel, continuation);
    }

    public static final <K, V> Object toMap(ReceiveChannel<? extends Pair<? extends K, ? extends V>> receiveChannel, Continuation<? super Map<K, ? extends V>> continuation) {
        return ChannelsKt.toMap(receiveChannel, new LinkedHashMap(), continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b9 A[Catch:{ all -> 0x0100 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ba A[Catch:{ all -> 0x0100 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ca A[Catch:{ all -> 0x0100 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <K, V, M extends java.util.Map<? super K, ? super V>> java.lang.Object toMap(kotlinx.coroutines.channels.ReceiveChannel<? extends kotlin.Pair<? extends K, ? extends V>> r13, M r14, kotlin.coroutines.Continuation<? super M> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toMap$2
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toMap$2 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toMap$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toMap$2 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toMap$2
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0090
            if (r2 == r4) goto L_0x0062
            if (r2 != r3) goto L_0x005a
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008c }
            if (r9 != 0) goto L_0x0055
            r10 = r8
            r8 = r13
            r13 = r10
        L_0x004c:
            r11 = r0
            r0 = r14
            r14 = r6
            r6 = r11
            r12 = r7
            r7 = r1
            r1 = r12
            goto L_0x00e8
        L_0x0055:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x008c }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x008c }
            throw r13     // Catch:{ all -> 0x008c }
        L_0x005a:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0062:
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008c }
            if (r9 != 0) goto L_0x0087
            r10 = r8
            r8 = r15
            r15 = r5
            r5 = r10
            goto L_0x00c2
        L_0x0087:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x008c }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x008c }
            throw r13     // Catch:{ all -> 0x008c }
        L_0x008c:
            r13 = move-exception
            r15 = r5
            goto L_0x0105
        L_0x0090:
            boolean r2 = r15 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x010b
            r15 = 0
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            kotlinx.coroutines.channels.ChannelIterator r2 = r13.iterator()     // Catch:{ all -> 0x0102 }
            r5 = r0
            r6 = r1
            r7 = r2
            r0 = r13
            r1 = r14
            r2 = r15
            r14 = r0
            r15 = r14
        L_0x00a3:
            r5.L$0 = r13     // Catch:{ all -> 0x0100 }
            r5.L$1 = r1     // Catch:{ all -> 0x0100 }
            r5.L$2 = r14     // Catch:{ all -> 0x0100 }
            r5.L$3 = r15     // Catch:{ all -> 0x0100 }
            r5.L$4 = r2     // Catch:{ all -> 0x0100 }
            r5.L$5 = r0     // Catch:{ all -> 0x0100 }
            r5.L$6 = r7     // Catch:{ all -> 0x0100 }
            r5.label = r4     // Catch:{ all -> 0x0100 }
            java.lang.Object r8 = r7.hasNext(r5)     // Catch:{ all -> 0x0100 }
            if (r8 != r6) goto L_0x00ba
            return r6
        L_0x00ba:
            r10 = r5
            r5 = r13
            r13 = r7
            r7 = r1
            r1 = r6
            r6 = r14
            r14 = r0
            r0 = r10
        L_0x00c2:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x0100 }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x0100 }
            if (r8 == 0) goto L_0x00fa
            r0.L$0 = r5     // Catch:{ all -> 0x0100 }
            r0.L$1 = r7     // Catch:{ all -> 0x0100 }
            r0.L$2 = r6     // Catch:{ all -> 0x0100 }
            r0.L$3 = r15     // Catch:{ all -> 0x0100 }
            r0.L$4 = r2     // Catch:{ all -> 0x0100 }
            r0.L$5 = r14     // Catch:{ all -> 0x0100 }
            r0.L$6 = r13     // Catch:{ all -> 0x0100 }
            r0.label = r3     // Catch:{ all -> 0x0100 }
            java.lang.Object r8 = r13.next(r0)     // Catch:{ all -> 0x0100 }
            if (r8 != r1) goto L_0x00e1
            return r1
        L_0x00e1:
            r10 = r8
            r8 = r13
            r13 = r5
            r5 = r15
            r15 = r10
            goto L_0x004c
        L_0x00e8:
            kotlin.Pair r15 = (kotlin.Pair) r15     // Catch:{ all -> 0x008c }
            java.lang.Object r9 = r15.getFirst()     // Catch:{ all -> 0x008c }
            java.lang.Object r15 = r15.getSecond()     // Catch:{ all -> 0x008c }
            r1.put(r9, r15)     // Catch:{ all -> 0x008c }
            r15 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            goto L_0x00a3
        L_0x00fa:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0100 }
            r15.cancel(r2)
            return r7
        L_0x0100:
            r13 = move-exception
            goto L_0x0105
        L_0x0102:
            r14 = move-exception
            r15 = r13
            r13 = r14
        L_0x0105:
            throw r13     // Catch:{ all -> 0x0106 }
        L_0x0106:
            r14 = move-exception
            r15.cancel(r13)
            throw r14
        L_0x010b:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15
            java.lang.Throwable r13 = r15.exception
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.toMap(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final <E> Object toMutableList(ReceiveChannel<? extends E> receiveChannel, Continuation<? super List<E>> continuation) {
        return ChannelsKt.toCollection(receiveChannel, new ArrayList(), continuation);
    }

    public static final <E> Object toSet(ReceiveChannel<? extends E> receiveChannel, Continuation<? super Set<? extends E>> continuation) {
        return ChannelsKt.toMutableSet(receiveChannel, continuation);
    }

    public static /* synthetic */ ReceiveChannel flatMap$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.flatMap(receiveChannel, coroutineContext, function2);
    }

    public static final <E, R> ReceiveChannel<R> flatMap(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super ReceiveChannel<? extends R>>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$flatMap$1(receiveChannel, function2, (Continuation) null), 2, (Object) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e1 A[Catch:{ all -> 0x0141 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e2 A[Catch:{ all -> 0x0141 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f9 A[Catch:{ all -> 0x0141 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0126 A[Catch:{ all -> 0x0141 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K> java.lang.Object groupBy(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, kotlin.jvm.functions.Function1<? super E, ? extends K> r19, kotlin.coroutines.Continuation<? super java.util.Map<K, ? extends java.util.List<? extends E>>> r20) {
        /*
            r0 = r20
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00a8
            if (r3 == r5) goto L_0x006e
            if (r3 != r4) goto L_0x0066
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            java.util.Map r10 = (java.util.Map) r10
            java.lang.Object r11 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a3 }
            if (r14 != 0) goto L_0x0061
            r16 = r9
            r9 = r1
            r1 = r11
            r11 = r7
            r7 = r12
            r12 = r3
            r3 = r8
        L_0x005b:
            r8 = r10
            r10 = r2
            r2 = r16
            goto L_0x011c
        L_0x0061:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a3 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a3 }
            throw r0     // Catch:{ all -> 0x00a3 }
        L_0x0066:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006e:
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            java.util.Map r10 = (java.util.Map) r10
            java.lang.Object r11 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a3 }
            if (r14 != 0) goto L_0x009e
            r16 = r12
            r12 = r3
            r3 = r8
            r8 = r16
            goto L_0x00f1
        L_0x009e:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a3 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a3 }
            throw r0     // Catch:{ all -> 0x00a3 }
        L_0x00a3:
            r0 = move-exception
            r1 = r0
            r3 = r8
            goto L_0x0147
        L_0x00a8:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0154
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r6 = r18.iterator()     // Catch:{ all -> 0x0143 }
            r7 = r19
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r12 = r6
            r0 = r18
            r1 = r0
            r2 = r1
            r3 = r2
            r6 = r3
        L_0x00c7:
            r9.L$0 = r0     // Catch:{ all -> 0x0141 }
            r9.L$1 = r7     // Catch:{ all -> 0x0141 }
            r9.L$2 = r1     // Catch:{ all -> 0x0141 }
            r9.L$3 = r8     // Catch:{ all -> 0x0141 }
            r9.L$4 = r2     // Catch:{ all -> 0x0141 }
            r9.L$5 = r3     // Catch:{ all -> 0x0141 }
            r9.L$6 = r11     // Catch:{ all -> 0x0141 }
            r9.L$7 = r6     // Catch:{ all -> 0x0141 }
            r9.L$8 = r12     // Catch:{ all -> 0x0141 }
            r9.label = r5     // Catch:{ all -> 0x0141 }
            java.lang.Object r13 = r12.hasNext(r9)     // Catch:{ all -> 0x0141 }
            if (r13 != r10) goto L_0x00e2
            return r10
        L_0x00e2:
            r16 = r13
            r13 = r0
            r0 = r16
            r17 = r11
            r11 = r1
            r1 = r9
            r9 = r2
            r2 = r10
            r10 = r8
            r8 = r7
            r7 = r17
        L_0x00f1:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0141 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0141 }
            if (r0 == 0) goto L_0x0135
            r1.L$0 = r13     // Catch:{ all -> 0x0141 }
            r1.L$1 = r8     // Catch:{ all -> 0x0141 }
            r1.L$2 = r11     // Catch:{ all -> 0x0141 }
            r1.L$3 = r10     // Catch:{ all -> 0x0141 }
            r1.L$4 = r9     // Catch:{ all -> 0x0141 }
            r1.L$5 = r3     // Catch:{ all -> 0x0141 }
            r1.L$6 = r7     // Catch:{ all -> 0x0141 }
            r1.L$7 = r6     // Catch:{ all -> 0x0141 }
            r1.L$8 = r12     // Catch:{ all -> 0x0141 }
            r1.label = r4     // Catch:{ all -> 0x0141 }
            java.lang.Object r0 = r12.next(r1)     // Catch:{ all -> 0x0141 }
            if (r0 != r2) goto L_0x0114
            return r2
        L_0x0114:
            r16 = r9
            r9 = r1
            r1 = r11
            r11 = r7
            r7 = r8
            goto L_0x005b
        L_0x011c:
            java.lang.Object r14 = r7.invoke(r0)     // Catch:{ all -> 0x0141 }
            java.lang.Object r15 = r8.get(r14)     // Catch:{ all -> 0x0141 }
            if (r15 != 0) goto L_0x012e
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ all -> 0x0141 }
            r15.<init>()     // Catch:{ all -> 0x0141 }
            r8.put(r14, r15)     // Catch:{ all -> 0x0141 }
        L_0x012e:
            java.util.List r15 = (java.util.List) r15     // Catch:{ all -> 0x0141 }
            r15.add(r0)     // Catch:{ all -> 0x0141 }
            r0 = r13
            goto L_0x00c7
        L_0x0135:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0141 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r3.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            return r10
        L_0x0141:
            r0 = move-exception
            goto L_0x0146
        L_0x0143:
            r0 = move-exception
            r3 = r18
        L_0x0146:
            r1 = r0
        L_0x0147:
            throw r1     // Catch:{ all -> 0x0148 }
        L_0x0148:
            r0 = move-exception
            r2 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r3.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r2
        L_0x0154:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.groupBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0052, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0053, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r7.cancel(r8);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005c, code lost:
        throw r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object groupBy$$forInline(kotlinx.coroutines.channels.ReceiveChannel r7, kotlin.jvm.functions.Function1 r8, kotlin.coroutines.Continuation r9) {
        /*
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            r1 = 0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = 1
            kotlinx.coroutines.channels.ChannelIterator r3 = r7.iterator()     // Catch:{ all -> 0x0050 }
        L_0x000f:
            r4 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0050 }
            java.lang.Object r5 = r3.hasNext(r9)     // Catch:{ all -> 0x0050 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0050 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0050 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0050 }
            if (r5 == 0) goto L_0x0044
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0050 }
            java.lang.Object r4 = r3.next(r9)     // Catch:{ all -> 0x0050 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0050 }
            java.lang.Object r5 = r8.invoke(r4)     // Catch:{ all -> 0x0050 }
            java.lang.Object r6 = r0.get(r5)     // Catch:{ all -> 0x0050 }
            if (r6 != 0) goto L_0x003e
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x0050 }
            r6.<init>()     // Catch:{ all -> 0x0050 }
            r0.put(r5, r6)     // Catch:{ all -> 0x0050 }
        L_0x003e:
            java.util.List r6 = (java.util.List) r6     // Catch:{ all -> 0x0050 }
            r6.add(r4)     // Catch:{ all -> 0x0050 }
            goto L_0x000f
        L_0x0044:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0050 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r7.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            return r0
        L_0x0050:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0052 }
        L_0x0052:
            r9 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r7.cancel(r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.groupBy$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f2 A[Catch:{ all -> 0x0167 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x010d A[Catch:{ all -> 0x0167 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0142 A[Catch:{ all -> 0x0167 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K, V> java.lang.Object groupBy(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r20, kotlin.jvm.functions.Function1<? super E, ? extends K> r21, kotlin.jvm.functions.Function1<? super E, ? extends V> r22, kotlin.coroutines.Continuation<? super java.util.Map<K, ? extends java.util.List<? extends V>>> r23) {
        /*
            r0 = r23
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$2
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$2 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$2) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$2 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupBy$2
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00b4
            if (r3 == r5) goto L_0x0076
            if (r3 != r4) goto L_0x006e
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$7
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            java.util.Map r10 = (java.util.Map) r10
            java.lang.Object r11 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$1
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00af }
            if (r15 != 0) goto L_0x0069
            r17 = r10
            r10 = r1
            r1 = r11
            r11 = r2
            r2 = r9
            r9 = r17
            r18 = r13
            r13 = r3
            r3 = r8
            r8 = r12
            r12 = r7
            r7 = r18
            goto L_0x0138
        L_0x0069:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00af }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00af }
            throw r0     // Catch:{ all -> 0x00af }
        L_0x006e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0076:
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$7
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            java.util.Map r10 = (java.util.Map) r10
            java.lang.Object r11 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$1
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00af }
            if (r15 != 0) goto L_0x00aa
            r17 = r13
            r13 = r3
            r3 = r8
            r8 = r17
            goto L_0x0105
        L_0x00aa:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00af }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00af }
            throw r0     // Catch:{ all -> 0x00af }
        L_0x00af:
            r0 = move-exception
            r1 = r0
            r3 = r8
            goto L_0x016d
        L_0x00b4:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x017a
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r6 = r20.iterator()     // Catch:{ all -> 0x0169 }
            r7 = r21
            r8 = r22
            r9 = r0
            r10 = r1
            r11 = r2
            r12 = r3
            r13 = r6
            r0 = r20
            r1 = r0
            r2 = r1
            r3 = r2
            r6 = r3
        L_0x00d5:
            r10.L$0 = r0     // Catch:{ all -> 0x0167 }
            r10.L$1 = r7     // Catch:{ all -> 0x0167 }
            r10.L$2 = r8     // Catch:{ all -> 0x0167 }
            r10.L$3 = r1     // Catch:{ all -> 0x0167 }
            r10.L$4 = r9     // Catch:{ all -> 0x0167 }
            r10.L$5 = r2     // Catch:{ all -> 0x0167 }
            r10.L$6 = r3     // Catch:{ all -> 0x0167 }
            r10.L$7 = r12     // Catch:{ all -> 0x0167 }
            r10.L$8 = r6     // Catch:{ all -> 0x0167 }
            r10.L$9 = r13     // Catch:{ all -> 0x0167 }
            r10.label = r5     // Catch:{ all -> 0x0167 }
            java.lang.Object r14 = r13.hasNext(r10)     // Catch:{ all -> 0x0167 }
            if (r14 != r11) goto L_0x00f2
            return r11
        L_0x00f2:
            r17 = r14
            r14 = r0
            r0 = r17
            r18 = r11
            r11 = r1
            r1 = r10
            r10 = r9
            r9 = r2
            r2 = r18
            r19 = r8
            r8 = r7
            r7 = r12
            r12 = r19
        L_0x0105:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0167 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0167 }
            if (r0 == 0) goto L_0x015b
            r1.L$0 = r14     // Catch:{ all -> 0x0167 }
            r1.L$1 = r8     // Catch:{ all -> 0x0167 }
            r1.L$2 = r12     // Catch:{ all -> 0x0167 }
            r1.L$3 = r11     // Catch:{ all -> 0x0167 }
            r1.L$4 = r10     // Catch:{ all -> 0x0167 }
            r1.L$5 = r9     // Catch:{ all -> 0x0167 }
            r1.L$6 = r3     // Catch:{ all -> 0x0167 }
            r1.L$7 = r7     // Catch:{ all -> 0x0167 }
            r1.L$8 = r6     // Catch:{ all -> 0x0167 }
            r1.L$9 = r13     // Catch:{ all -> 0x0167 }
            r1.label = r4     // Catch:{ all -> 0x0167 }
            java.lang.Object r0 = r13.next(r1)     // Catch:{ all -> 0x0167 }
            if (r0 != r2) goto L_0x012a
            return r2
        L_0x012a:
            r17 = r10
            r10 = r1
            r1 = r11
            r11 = r2
            r2 = r9
            r9 = r17
            r18 = r12
            r12 = r7
            r7 = r8
            r8 = r18
        L_0x0138:
            java.lang.Object r15 = r7.invoke(r0)     // Catch:{ all -> 0x0167 }
            java.lang.Object r16 = r9.get(r15)     // Catch:{ all -> 0x0167 }
            if (r16 != 0) goto L_0x014c
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x0167 }
            r4.<init>()     // Catch:{ all -> 0x0167 }
            r9.put(r15, r4)     // Catch:{ all -> 0x0167 }
            r16 = r4
        L_0x014c:
            r4 = r16
            java.util.List r4 = (java.util.List) r4     // Catch:{ all -> 0x0167 }
            java.lang.Object r0 = r8.invoke(r0)     // Catch:{ all -> 0x0167 }
            r4.add(r0)     // Catch:{ all -> 0x0167 }
            r0 = r14
            r4 = 2
            goto L_0x00d5
        L_0x015b:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0167 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r3.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            return r10
        L_0x0167:
            r0 = move-exception
            goto L_0x016c
        L_0x0169:
            r0 = move-exception
            r3 = r20
        L_0x016c:
            r1 = r0
        L_0x016d:
            throw r1     // Catch:{ all -> 0x016e }
        L_0x016e:
            r0 = move-exception
            r2 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r3.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r2
        L_0x017a:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.groupBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0056, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0057, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r7.cancel(r8);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0060, code lost:
        throw r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object groupBy$$forInline(kotlinx.coroutines.channels.ReceiveChannel r7, kotlin.jvm.functions.Function1 r8, kotlin.jvm.functions.Function1 r9, kotlin.coroutines.Continuation r10) {
        /*
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            r1 = 0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = 1
            kotlinx.coroutines.channels.ChannelIterator r3 = r7.iterator()     // Catch:{ all -> 0x0054 }
        L_0x000f:
            r4 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0054 }
            java.lang.Object r5 = r3.hasNext(r10)     // Catch:{ all -> 0x0054 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0054 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0054 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0054 }
            if (r5 == 0) goto L_0x0048
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0054 }
            java.lang.Object r4 = r3.next(r10)     // Catch:{ all -> 0x0054 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0054 }
            java.lang.Object r5 = r8.invoke(r4)     // Catch:{ all -> 0x0054 }
            java.lang.Object r6 = r0.get(r5)     // Catch:{ all -> 0x0054 }
            if (r6 != 0) goto L_0x003e
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x0054 }
            r6.<init>()     // Catch:{ all -> 0x0054 }
            r0.put(r5, r6)     // Catch:{ all -> 0x0054 }
        L_0x003e:
            java.util.List r6 = (java.util.List) r6     // Catch:{ all -> 0x0054 }
            java.lang.Object r4 = r9.invoke(r4)     // Catch:{ all -> 0x0054 }
            r6.add(r4)     // Catch:{ all -> 0x0054 }
            goto L_0x000f
        L_0x0048:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0054 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r7.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            return r0
        L_0x0054:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0056 }
        L_0x0056:
            r9 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r7.cancel(r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.groupBy$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d2 A[Catch:{ all -> 0x0139 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ed A[Catch:{ all -> 0x0139 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x011e A[Catch:{ all -> 0x0139 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K, M extends java.util.Map<? super K, java.util.List<E>>> java.lang.Object groupByTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r19, M r20, kotlin.jvm.functions.Function1<? super E, ? extends K> r21, kotlin.coroutines.Continuation<? super M> r22) {
        /*
            r0 = r22
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00a1
            if (r3 == r5) goto L_0x006e
            if (r3 != r4) goto L_0x0066
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$1
            java.util.Map r11 = (java.util.Map) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009c }
            if (r13 != 0) goto L_0x0061
            r15 = r9
            r9 = r1
            r1 = r15
            r16 = r10
            r10 = r2
            r2 = r8
            r8 = r7
            r7 = r16
            r17 = r11
            r11 = r3
            r3 = r6
            r6 = r17
            goto L_0x0114
        L_0x0061:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009c }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009c }
            throw r0     // Catch:{ all -> 0x009c }
        L_0x0066:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006e:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$1
            java.util.Map r11 = (java.util.Map) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009c }
            if (r13 != 0) goto L_0x0097
            r15 = r10
            r10 = r2
            r2 = r8
            r8 = r15
            goto L_0x00e5
        L_0x0097:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009c }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009c }
            throw r0     // Catch:{ all -> 0x009c }
        L_0x009c:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x013f
        L_0x00a1:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x014c
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlinx.coroutines.channels.ChannelIterator r3 = r19.iterator()     // Catch:{ all -> 0x013b }
            r6 = r20
            r7 = r21
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r0 = r19
            r1 = r0
            r2 = r1
            r3 = r2
        L_0x00b9:
            r9.L$0 = r0     // Catch:{ all -> 0x0139 }
            r9.L$1 = r6     // Catch:{ all -> 0x0139 }
            r9.L$2 = r7     // Catch:{ all -> 0x0139 }
            r9.L$3 = r1     // Catch:{ all -> 0x0139 }
            r9.L$4 = r2     // Catch:{ all -> 0x0139 }
            r9.L$5 = r8     // Catch:{ all -> 0x0139 }
            r9.L$6 = r3     // Catch:{ all -> 0x0139 }
            r9.L$7 = r11     // Catch:{ all -> 0x0139 }
            r9.label = r5     // Catch:{ all -> 0x0139 }
            java.lang.Object r12 = r11.hasNext(r9)     // Catch:{ all -> 0x0139 }
            if (r12 != r10) goto L_0x00d2
            return r10
        L_0x00d2:
            r15 = r12
            r12 = r0
            r0 = r15
            r16 = r9
            r9 = r1
            r1 = r16
            r17 = r6
            r6 = r3
            r3 = r11
            r11 = r17
            r18 = r8
            r8 = r7
            r7 = r18
        L_0x00e5:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0139 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0139 }
            if (r0 == 0) goto L_0x012d
            r1.L$0 = r12     // Catch:{ all -> 0x0139 }
            r1.L$1 = r11     // Catch:{ all -> 0x0139 }
            r1.L$2 = r8     // Catch:{ all -> 0x0139 }
            r1.L$3 = r9     // Catch:{ all -> 0x0139 }
            r1.L$4 = r2     // Catch:{ all -> 0x0139 }
            r1.L$5 = r7     // Catch:{ all -> 0x0139 }
            r1.L$6 = r6     // Catch:{ all -> 0x0139 }
            r1.L$7 = r3     // Catch:{ all -> 0x0139 }
            r1.label = r4     // Catch:{ all -> 0x0139 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0139 }
            if (r0 != r10) goto L_0x0106
            return r10
        L_0x0106:
            r15 = r9
            r9 = r1
            r1 = r15
            r16 = r11
            r11 = r3
            r3 = r6
            r6 = r16
            r17 = r8
            r8 = r7
            r7 = r17
        L_0x0114:
            java.lang.Object r13 = r7.invoke(r0)     // Catch:{ all -> 0x0139 }
            java.lang.Object r14 = r6.get(r13)     // Catch:{ all -> 0x0139 }
            if (r14 != 0) goto L_0x0126
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch:{ all -> 0x0139 }
            r14.<init>()     // Catch:{ all -> 0x0139 }
            r6.put(r13, r14)     // Catch:{ all -> 0x0139 }
        L_0x0126:
            java.util.List r14 = (java.util.List) r14     // Catch:{ all -> 0x0139 }
            r14.add(r0)     // Catch:{ all -> 0x0139 }
            r0 = r12
            goto L_0x00b9
        L_0x012d:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0139 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            return r11
        L_0x0139:
            r0 = move-exception
            goto L_0x013e
        L_0x013b:
            r0 = move-exception
            r2 = r19
        L_0x013e:
            r1 = r0
        L_0x013f:
            throw r1     // Catch:{ all -> 0x0140 }
        L_0x0140:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x014c:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.groupByTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e0 A[Catch:{ all -> 0x0148 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e1 A[Catch:{ all -> 0x0148 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00fc A[Catch:{ all -> 0x0148 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0129 A[Catch:{ all -> 0x0148 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, K, V, M extends java.util.Map<? super K, java.util.List<V>>> java.lang.Object groupByTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r19, M r20, kotlin.jvm.functions.Function1<? super E, ? extends K> r21, kotlin.jvm.functions.Function1<? super E, ? extends V> r22, kotlin.coroutines.Continuation<? super M> r23) {
        /*
            r0 = r23
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$3 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$3 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$groupByTo$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00ac
            if (r3 == r5) goto L_0x0072
            if (r3 != r4) goto L_0x006a
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$1
            java.util.Map r12 = (java.util.Map) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a7 }
            if (r14 != 0) goto L_0x0065
            r16 = r10
            r10 = r1
            r1 = r9
            r9 = r7
            r7 = r11
            r11 = r2
            r2 = r8
        L_0x005b:
            r8 = r16
            r17 = r12
            r12 = r3
            r3 = r6
            r6 = r17
            goto L_0x011f
        L_0x0065:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a7 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a7 }
            throw r0     // Catch:{ all -> 0x00a7 }
        L_0x006a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0072:
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$1
            java.util.Map r12 = (java.util.Map) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a7 }
            if (r14 != 0) goto L_0x00a2
            r16 = r11
            r11 = r2
            r2 = r8
            r8 = r16
            goto L_0x00f4
        L_0x00a2:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a7 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a7 }
            throw r0     // Catch:{ all -> 0x00a7 }
        L_0x00a7:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x014e
        L_0x00ac:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x015b
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlinx.coroutines.channels.ChannelIterator r3 = r19.iterator()     // Catch:{ all -> 0x014a }
            r6 = r20
            r7 = r21
            r8 = r22
            r9 = r0
            r10 = r1
            r11 = r2
            r12 = r3
            r0 = r19
            r1 = r0
            r2 = r1
            r3 = r2
        L_0x00c6:
            r10.L$0 = r0     // Catch:{ all -> 0x0148 }
            r10.L$1 = r6     // Catch:{ all -> 0x0148 }
            r10.L$2 = r7     // Catch:{ all -> 0x0148 }
            r10.L$3 = r8     // Catch:{ all -> 0x0148 }
            r10.L$4 = r1     // Catch:{ all -> 0x0148 }
            r10.L$5 = r2     // Catch:{ all -> 0x0148 }
            r10.L$6 = r9     // Catch:{ all -> 0x0148 }
            r10.L$7 = r3     // Catch:{ all -> 0x0148 }
            r10.L$8 = r12     // Catch:{ all -> 0x0148 }
            r10.label = r5     // Catch:{ all -> 0x0148 }
            java.lang.Object r13 = r12.hasNext(r10)     // Catch:{ all -> 0x0148 }
            if (r13 != r11) goto L_0x00e1
            return r11
        L_0x00e1:
            r16 = r13
            r13 = r0
            r0 = r16
            r17 = r9
            r9 = r1
            r1 = r10
            r10 = r8
            r8 = r7
            r7 = r17
            r18 = r6
            r6 = r3
            r3 = r12
            r12 = r18
        L_0x00f4:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0148 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0148 }
            if (r0 == 0) goto L_0x013c
            r1.L$0 = r13     // Catch:{ all -> 0x0148 }
            r1.L$1 = r12     // Catch:{ all -> 0x0148 }
            r1.L$2 = r8     // Catch:{ all -> 0x0148 }
            r1.L$3 = r10     // Catch:{ all -> 0x0148 }
            r1.L$4 = r9     // Catch:{ all -> 0x0148 }
            r1.L$5 = r2     // Catch:{ all -> 0x0148 }
            r1.L$6 = r7     // Catch:{ all -> 0x0148 }
            r1.L$7 = r6     // Catch:{ all -> 0x0148 }
            r1.L$8 = r3     // Catch:{ all -> 0x0148 }
            r1.label = r4     // Catch:{ all -> 0x0148 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0148 }
            if (r0 != r11) goto L_0x0117
            return r11
        L_0x0117:
            r16 = r10
            r10 = r1
            r1 = r9
            r9 = r7
            r7 = r8
            goto L_0x005b
        L_0x011f:
            java.lang.Object r14 = r7.invoke(r0)     // Catch:{ all -> 0x0148 }
            java.lang.Object r15 = r6.get(r14)     // Catch:{ all -> 0x0148 }
            if (r15 != 0) goto L_0x0131
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ all -> 0x0148 }
            r15.<init>()     // Catch:{ all -> 0x0148 }
            r6.put(r14, r15)     // Catch:{ all -> 0x0148 }
        L_0x0131:
            java.util.List r15 = (java.util.List) r15     // Catch:{ all -> 0x0148 }
            java.lang.Object r0 = r8.invoke(r0)     // Catch:{ all -> 0x0148 }
            r15.add(r0)     // Catch:{ all -> 0x0148 }
            r0 = r13
            goto L_0x00c6
        L_0x013c:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0148 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            return r12
        L_0x0148:
            r0 = move-exception
            goto L_0x014d
        L_0x014a:
            r0 = move-exception
            r2 = r19
        L_0x014d:
            r1 = r0
        L_0x014e:
            throw r1     // Catch:{ all -> 0x014f }
        L_0x014f:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x015b:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.groupByTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ ReceiveChannel map$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.map(receiveChannel, coroutineContext, function2);
    }

    public static final <E, R> ReceiveChannel<R> map(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$map$1(receiveChannel, function2, (Continuation) null), 2, (Object) null);
    }

    public static /* synthetic */ ReceiveChannel mapIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.mapIndexed(receiveChannel, coroutineContext, function3);
    }

    public static final <E, R> ReceiveChannel<R> mapIndexed(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function3, "transform");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$mapIndexed$1(receiveChannel, function3, (Continuation) null), 2, (Object) null);
    }

    public static /* synthetic */ ReceiveChannel mapIndexedNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.mapIndexedNotNull(receiveChannel, coroutineContext, function3);
    }

    public static final <E, R> ReceiveChannel<R> mapIndexedNotNull(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function3, "transform");
        return ChannelsKt.filterNotNull(ChannelsKt.mapIndexed(receiveChannel, coroutineContext, function3));
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f3 A[Catch:{ all -> 0x016f }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x010e A[Catch:{ all -> 0x016f }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0156 A[Catch:{ all -> 0x016f }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R, C extends java.util.Collection<? super R>> java.lang.Object mapIndexedNotNullTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r19, C r20, kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super E, ? extends R> r21, kotlin.coroutines.Continuation<? super C> r22) {
        /*
            r0 = r22
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00b4
            if (r3 == r5) goto L_0x0076
            if (r3 != r4) goto L_0x006e
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$7
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            java.lang.Object r13 = r1.L$1
            java.util.Collection r13 = (java.util.Collection) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00af }
            if (r15 != 0) goto L_0x0069
            r16 = r10
            r10 = r1
            r1 = r11
            r11 = r2
            r2 = r9
            r9 = r16
            r17 = r13
            r13 = r3
            r3 = r8
            r8 = r12
            r12 = r7
            r7 = r17
            goto L_0x0139
        L_0x0069:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00af }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00af }
            throw r0     // Catch:{ all -> 0x00af }
        L_0x006e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0076:
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$7
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            java.lang.Object r13 = r1.L$1
            java.util.Collection r13 = (java.util.Collection) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00af }
            if (r15 != 0) goto L_0x00aa
            r16 = r13
            r13 = r3
            r3 = r8
            r8 = r16
            goto L_0x0106
        L_0x00aa:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00af }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00af }
            throw r0     // Catch:{ all -> 0x00af }
        L_0x00af:
            r0 = move-exception
            r1 = r0
            r3 = r8
            goto L_0x0175
        L_0x00b4:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0183
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r6 = r19.iterator()     // Catch:{ all -> 0x0171 }
            r7 = r20
            r8 = r21
            r9 = r0
            r10 = r1
            r11 = r2
            r12 = r3
            r13 = r6
            r0 = r19
            r1 = r0
            r2 = r1
            r3 = r2
            r6 = r3
        L_0x00d6:
            r10.L$0 = r0     // Catch:{ all -> 0x016f }
            r10.L$1 = r7     // Catch:{ all -> 0x016f }
            r10.L$2 = r8     // Catch:{ all -> 0x016f }
            r10.L$3 = r1     // Catch:{ all -> 0x016f }
            r10.L$4 = r9     // Catch:{ all -> 0x016f }
            r10.L$5 = r2     // Catch:{ all -> 0x016f }
            r10.L$6 = r3     // Catch:{ all -> 0x016f }
            r10.L$7 = r12     // Catch:{ all -> 0x016f }
            r10.L$8 = r6     // Catch:{ all -> 0x016f }
            r10.L$9 = r13     // Catch:{ all -> 0x016f }
            r10.label = r5     // Catch:{ all -> 0x016f }
            java.lang.Object r14 = r13.hasNext(r10)     // Catch:{ all -> 0x016f }
            if (r14 != r11) goto L_0x00f3
            return r11
        L_0x00f3:
            r16 = r14
            r14 = r0
            r0 = r16
            r17 = r11
            r11 = r1
            r1 = r10
            r10 = r9
            r9 = r2
            r2 = r17
            r18 = r8
            r8 = r7
            r7 = r12
            r12 = r18
        L_0x0106:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x016f }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x016f }
            if (r0 == 0) goto L_0x0162
            r1.L$0 = r14     // Catch:{ all -> 0x016f }
            r1.L$1 = r8     // Catch:{ all -> 0x016f }
            r1.L$2 = r12     // Catch:{ all -> 0x016f }
            r1.L$3 = r11     // Catch:{ all -> 0x016f }
            r1.L$4 = r10     // Catch:{ all -> 0x016f }
            r1.L$5 = r9     // Catch:{ all -> 0x016f }
            r1.L$6 = r3     // Catch:{ all -> 0x016f }
            r1.L$7 = r7     // Catch:{ all -> 0x016f }
            r1.L$8 = r6     // Catch:{ all -> 0x016f }
            r1.L$9 = r13     // Catch:{ all -> 0x016f }
            r1.label = r4     // Catch:{ all -> 0x016f }
            java.lang.Object r0 = r13.next(r1)     // Catch:{ all -> 0x016f }
            if (r0 != r2) goto L_0x012b
            return r2
        L_0x012b:
            r16 = r10
            r10 = r1
            r1 = r11
            r11 = r2
            r2 = r9
            r9 = r16
            r17 = r12
            r12 = r7
            r7 = r8
            r8 = r17
        L_0x0139:
            kotlin.collections.IndexedValue r15 = new kotlin.collections.IndexedValue     // Catch:{ all -> 0x016f }
            int r4 = r9.element     // Catch:{ all -> 0x016f }
            int r5 = r4 + 1
            r9.element = r5     // Catch:{ all -> 0x016f }
            r15.<init>(r4, r0)     // Catch:{ all -> 0x016f }
            int r0 = r15.component1()     // Catch:{ all -> 0x016f }
            java.lang.Object r4 = r15.component2()     // Catch:{ all -> 0x016f }
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)     // Catch:{ all -> 0x016f }
            java.lang.Object r0 = r8.invoke(r0, r4)     // Catch:{ all -> 0x016f }
            if (r0 == 0) goto L_0x015d
            boolean r0 = r7.add(r0)     // Catch:{ all -> 0x016f }
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)     // Catch:{ all -> 0x016f }
        L_0x015d:
            r0 = r14
            r4 = 2
            r5 = 1
            goto L_0x00d6
        L_0x0162:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x016f }
            r1 = 1
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r3.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r8
        L_0x016f:
            r0 = move-exception
            goto L_0x0174
        L_0x0171:
            r0 = move-exception
            r3 = r19
        L_0x0174:
            r1 = r0
        L_0x0175:
            throw r1     // Catch:{ all -> 0x0176 }
        L_0x0176:
            r0 = move-exception
            r2 = r0
            r4 = 1
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            r3.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r2
        L_0x0183:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapIndexedNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0136 A[Catch:{ all -> 0x01f1 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0137 A[Catch:{ all -> 0x01f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x014f A[Catch:{ all -> 0x01f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x01bd A[Catch:{ all -> 0x01f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R, C extends kotlinx.coroutines.channels.SendChannel<? super R>> java.lang.Object mapIndexedNotNullTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, C r19, kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super E, ? extends R> r20, kotlin.coroutines.Continuation<? super C> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$3 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$3 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedNotNullTo$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 3
            r5 = 2
            r6 = 1
            if (r3 == 0) goto L_0x00f8
            if (r3 == r6) goto L_0x00ba
            if (r3 == r5) goto L_0x007d
            if (r3 != r4) goto L_0x0075
            java.lang.Object r3 = r1.L$14
            java.lang.Object r3 = r1.L$13
            int r3 = r1.I$0
            java.lang.Object r3 = r1.L$12
            kotlin.collections.IndexedValue r3 = (kotlin.collections.IndexedValue) r3
            java.lang.Object r3 = r1.L$11
            java.lang.Object r3 = r1.L$10
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$7
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$4
            kotlin.jvm.internal.Ref$IntRef r11 = (kotlin.jvm.internal.Ref.IntRef) r11
            java.lang.Object r12 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r1.L$2
            kotlin.jvm.functions.Function2 r13 = (kotlin.jvm.functions.Function2) r13
            java.lang.Object r14 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r14 = (kotlinx.coroutines.channels.SendChannel) r14
            java.lang.Object r15 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r15 = (kotlinx.coroutines.channels.ReceiveChannel) r15
            boolean r4 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0070 }
            if (r4 != 0) goto L_0x006b
            r0 = 3
            goto L_0x01c1
        L_0x006b:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x0070 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x0070 }
            throw r0     // Catch:{ all -> 0x0070 }
        L_0x0070:
            r0 = move-exception
            r1 = r0
            r3 = r9
            goto L_0x01f7
        L_0x0075:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x007d:
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r4 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r7 = r1.L$7
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            java.lang.Object r13 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r13 = (kotlinx.coroutines.channels.SendChannel) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00f3 }
            if (r15 != 0) goto L_0x00b5
            r15 = r14
            r14 = r13
            r13 = r12
            r12 = r11
            r11 = r3
            r3 = r8
        L_0x00af:
            r8 = r7
            r7 = r4
            r4 = r10
            r10 = r9
            goto L_0x0172
        L_0x00b5:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00f3 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00f3 }
            throw r0     // Catch:{ all -> 0x00f3 }
        L_0x00ba:
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r4 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r7 = r1.L$7
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            java.lang.Object r13 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r13 = (kotlinx.coroutines.channels.SendChannel) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00f3 }
            if (r15 != 0) goto L_0x00ee
            r16 = r11
            r11 = r3
            r3 = r8
            r8 = r16
            goto L_0x0147
        L_0x00ee:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00f3 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00f3 }
            throw r0     // Catch:{ all -> 0x00f3 }
        L_0x00f3:
            r0 = move-exception
            r1 = r0
            r3 = r8
            goto L_0x01f7
        L_0x00f8:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0205
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r4 = r18.iterator()     // Catch:{ all -> 0x01f3 }
            r7 = r19
            r8 = r20
            r13 = r0
            r9 = r1
            r10 = r2
            r12 = r3
            r11 = r4
            r0 = r18
            r1 = r0
            r2 = r1
            r3 = r2
            r4 = r3
        L_0x011a:
            r9.L$0 = r0     // Catch:{ all -> 0x01f1 }
            r9.L$1 = r7     // Catch:{ all -> 0x01f1 }
            r9.L$2 = r8     // Catch:{ all -> 0x01f1 }
            r9.L$3 = r1     // Catch:{ all -> 0x01f1 }
            r9.L$4 = r13     // Catch:{ all -> 0x01f1 }
            r9.L$5 = r2     // Catch:{ all -> 0x01f1 }
            r9.L$6 = r3     // Catch:{ all -> 0x01f1 }
            r9.L$7 = r12     // Catch:{ all -> 0x01f1 }
            r9.L$8 = r4     // Catch:{ all -> 0x01f1 }
            r9.L$9 = r11     // Catch:{ all -> 0x01f1 }
            r9.label = r6     // Catch:{ all -> 0x01f1 }
            java.lang.Object r14 = r11.hasNext(r9)     // Catch:{ all -> 0x01f1 }
            if (r14 != r10) goto L_0x0137
            return r10
        L_0x0137:
            r16 = r14
            r14 = r0
            r0 = r16
            r17 = r8
            r8 = r1
            r1 = r9
            r9 = r2
            r2 = r10
            r10 = r13
            r13 = r7
            r7 = r12
            r12 = r17
        L_0x0147:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01f1 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01f1 }
            if (r0 == 0) goto L_0x01e4
            r1.L$0 = r14     // Catch:{ all -> 0x01f1 }
            r1.L$1 = r13     // Catch:{ all -> 0x01f1 }
            r1.L$2 = r12     // Catch:{ all -> 0x01f1 }
            r1.L$3 = r8     // Catch:{ all -> 0x01f1 }
            r1.L$4 = r10     // Catch:{ all -> 0x01f1 }
            r1.L$5 = r9     // Catch:{ all -> 0x01f1 }
            r1.L$6 = r3     // Catch:{ all -> 0x01f1 }
            r1.L$7 = r7     // Catch:{ all -> 0x01f1 }
            r1.L$8 = r4     // Catch:{ all -> 0x01f1 }
            r1.L$9 = r11     // Catch:{ all -> 0x01f1 }
            r1.label = r5     // Catch:{ all -> 0x01f1 }
            java.lang.Object r0 = r11.next(r1)     // Catch:{ all -> 0x01f1 }
            if (r0 != r2) goto L_0x016c
            return r2
        L_0x016c:
            r15 = r14
            r14 = r13
            r13 = r12
            r12 = r8
            goto L_0x00af
        L_0x0172:
            kotlin.collections.IndexedValue r9 = new kotlin.collections.IndexedValue     // Catch:{ all -> 0x01f1 }
            int r5 = r4.element     // Catch:{ all -> 0x01f1 }
            int r6 = r5 + 1
            r4.element = r6     // Catch:{ all -> 0x01f1 }
            r9.<init>(r5, r0)     // Catch:{ all -> 0x01f1 }
            int r5 = r9.component1()     // Catch:{ all -> 0x01f1 }
            java.lang.Object r6 = r9.component2()     // Catch:{ all -> 0x01f1 }
            r18 = r2
            java.lang.Integer r2 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)     // Catch:{ all -> 0x01f1 }
            java.lang.Object r2 = r13.invoke(r2, r6)     // Catch:{ all -> 0x01f1 }
            if (r2 == 0) goto L_0x01d3
            r1.L$0 = r15     // Catch:{ all -> 0x01f1 }
            r1.L$1 = r14     // Catch:{ all -> 0x01f1 }
            r1.L$2 = r13     // Catch:{ all -> 0x01f1 }
            r1.L$3 = r12     // Catch:{ all -> 0x01f1 }
            r1.L$4 = r4     // Catch:{ all -> 0x01f1 }
            r1.L$5 = r10     // Catch:{ all -> 0x01f1 }
            r1.L$6 = r3     // Catch:{ all -> 0x01f1 }
            r1.L$7 = r8     // Catch:{ all -> 0x01f1 }
            r1.L$8 = r7     // Catch:{ all -> 0x01f1 }
            r1.L$9 = r11     // Catch:{ all -> 0x01f1 }
            r1.L$10 = r0     // Catch:{ all -> 0x01f1 }
            r1.L$11 = r0     // Catch:{ all -> 0x01f1 }
            r1.L$12 = r9     // Catch:{ all -> 0x01f1 }
            r1.I$0 = r5     // Catch:{ all -> 0x01f1 }
            r1.L$13 = r6     // Catch:{ all -> 0x01f1 }
            r1.L$14 = r2     // Catch:{ all -> 0x01f1 }
            r0 = 3
            r1.label = r0     // Catch:{ all -> 0x01f1 }
            java.lang.Object r2 = r14.send(r2, r1)     // Catch:{ all -> 0x01f1 }
            r5 = r18
            if (r2 != r5) goto L_0x01bd
            return r5
        L_0x01bd:
            r9 = r3
            r2 = r5
            r3 = r11
            r11 = r4
        L_0x01c1:
            r4 = r7
            r7 = r14
            r16 = r9
            r9 = r1
            r1 = r12
            r12 = r8
            r8 = r13
            r13 = r11
            r11 = r3
            r3 = r16
            r17 = r10
            r10 = r2
            r2 = r17
            goto L_0x01df
        L_0x01d3:
            r5 = r18
            r0 = 3
            r9 = r1
            r2 = r10
            r1 = r12
            r10 = r5
            r12 = r8
            r8 = r13
            r13 = r4
            r4 = r7
            r7 = r14
        L_0x01df:
            r0 = r15
            r5 = 2
            r6 = 1
            goto L_0x011a
        L_0x01e4:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x01f1 }
            r1 = 1
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r3.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r13
        L_0x01f1:
            r0 = move-exception
            goto L_0x01f6
        L_0x01f3:
            r0 = move-exception
            r3 = r18
        L_0x01f6:
            r1 = r0
        L_0x01f7:
            throw r1     // Catch:{ all -> 0x01f8 }
        L_0x01f8:
            r0 = move-exception
            r2 = r0
            r4 = 1
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            r3.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r2
        L_0x0205:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapIndexedNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ee A[Catch:{ all -> 0x0155 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x010b A[Catch:{ all -> 0x0155 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R, C extends java.util.Collection<? super R>> java.lang.Object mapIndexedTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r20, C r21, kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super E, ? extends R> r22, kotlin.coroutines.Continuation<? super C> r23) {
        /*
            r0 = r23
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00b2
            if (r3 == r5) goto L_0x0078
            if (r3 != r4) goto L_0x0070
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            java.lang.Object r12 = r1.L$1
            java.util.Collection r12 = (java.util.Collection) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00ad }
            if (r14 != 0) goto L_0x006b
            r16 = r9
            r9 = r1
            r1 = r16
            r17 = r10
            r10 = r2
            r2 = r8
            r8 = r17
            r18 = r12
            r12 = r3
            r3 = r6
            r6 = r18
            r19 = r11
            r11 = r7
            r7 = r19
            goto L_0x0136
        L_0x006b:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00ad }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00ad }
            throw r0     // Catch:{ all -> 0x00ad }
        L_0x0070:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0078:
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            java.lang.Object r12 = r1.L$1
            java.util.Collection r12 = (java.util.Collection) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00ad }
            if (r14 != 0) goto L_0x00a8
            r16 = r10
            r10 = r2
            r2 = r8
            r8 = r16
            goto L_0x0103
        L_0x00a8:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00ad }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00ad }
            throw r0     // Catch:{ all -> 0x00ad }
        L_0x00ad:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x015b
        L_0x00b2:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0168
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r6 = r20.iterator()     // Catch:{ all -> 0x0157 }
            r7 = r22
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r12 = r6
            r0 = r20
            r1 = r0
            r2 = r1
            r3 = r2
            r6 = r21
        L_0x00d3:
            r9.L$0 = r0     // Catch:{ all -> 0x0155 }
            r9.L$1 = r6     // Catch:{ all -> 0x0155 }
            r9.L$2 = r7     // Catch:{ all -> 0x0155 }
            r9.L$3 = r8     // Catch:{ all -> 0x0155 }
            r9.L$4 = r1     // Catch:{ all -> 0x0155 }
            r9.L$5 = r2     // Catch:{ all -> 0x0155 }
            r9.L$6 = r11     // Catch:{ all -> 0x0155 }
            r9.L$7 = r3     // Catch:{ all -> 0x0155 }
            r9.L$8 = r12     // Catch:{ all -> 0x0155 }
            r9.label = r5     // Catch:{ all -> 0x0155 }
            java.lang.Object r13 = r12.hasNext(r9)     // Catch:{ all -> 0x0155 }
            if (r13 != r10) goto L_0x00ee
            return r10
        L_0x00ee:
            r16 = r13
            r13 = r0
            r0 = r16
            r17 = r9
            r9 = r1
            r1 = r17
            r18 = r6
            r6 = r3
            r3 = r12
            r12 = r18
            r19 = r11
            r11 = r7
            r7 = r19
        L_0x0103:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0155 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0155 }
            if (r0 == 0) goto L_0x0149
            r1.L$0 = r13     // Catch:{ all -> 0x0155 }
            r1.L$1 = r12     // Catch:{ all -> 0x0155 }
            r1.L$2 = r11     // Catch:{ all -> 0x0155 }
            r1.L$3 = r8     // Catch:{ all -> 0x0155 }
            r1.L$4 = r9     // Catch:{ all -> 0x0155 }
            r1.L$5 = r2     // Catch:{ all -> 0x0155 }
            r1.L$6 = r7     // Catch:{ all -> 0x0155 }
            r1.L$7 = r6     // Catch:{ all -> 0x0155 }
            r1.L$8 = r3     // Catch:{ all -> 0x0155 }
            r1.label = r4     // Catch:{ all -> 0x0155 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0155 }
            if (r0 != r10) goto L_0x0126
            return r10
        L_0x0126:
            r16 = r9
            r9 = r1
            r1 = r16
            r17 = r12
            r12 = r3
            r3 = r6
            r6 = r17
            r18 = r11
            r11 = r7
            r7 = r18
        L_0x0136:
            int r14 = r8.element     // Catch:{ all -> 0x0155 }
            int r15 = r14 + 1
            r8.element = r15     // Catch:{ all -> 0x0155 }
            java.lang.Integer r14 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r14)     // Catch:{ all -> 0x0155 }
            java.lang.Object r0 = r7.invoke(r14, r0)     // Catch:{ all -> 0x0155 }
            r6.add(r0)     // Catch:{ all -> 0x0155 }
            r0 = r13
            goto L_0x00d3
        L_0x0149:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0155 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            return r12
        L_0x0155:
            r0 = move-exception
            goto L_0x015a
        L_0x0157:
            r0 = move-exception
            r2 = r20
        L_0x015a:
            r1 = r0
        L_0x015b:
            throw r1     // Catch:{ all -> 0x015c }
        L_0x015c:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x0168:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapIndexedTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0127 A[Catch:{ all -> 0x01a5 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0128 A[Catch:{ all -> 0x01a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0146 A[Catch:{ all -> 0x01a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x018e A[Catch:{ all -> 0x01a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R, C extends kotlinx.coroutines.channels.SendChannel<? super R>> java.lang.Object mapIndexedTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r20, C r21, kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super E, ? extends R> r22, kotlin.coroutines.Continuation<? super C> r23) {
        /*
            r0 = r23
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$3 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$3 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexedTo$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 3
            r5 = 2
            r6 = 1
            if (r3 == 0) goto L_0x00ec
            if (r3 == r6) goto L_0x00b2
            if (r3 == r5) goto L_0x007d
            if (r3 != r4) goto L_0x0075
            java.lang.Object r3 = r1.L$10
            java.lang.Object r3 = r1.L$9
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$6
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$3
            kotlin.jvm.internal.Ref$IntRef r11 = (kotlin.jvm.internal.Ref.IntRef) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            java.lang.Object r13 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r13 = (kotlinx.coroutines.channels.SendChannel) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00e7 }
            if (r15 != 0) goto L_0x0070
            r0 = r14
            r16 = r9
            r9 = r1
            r1 = r10
            r10 = r2
            r2 = r16
        L_0x0062:
            r17 = r11
            r11 = r3
            r3 = r7
            r7 = r13
            r13 = r17
            r18 = r12
            r12 = r8
            r8 = r18
            goto L_0x0196
        L_0x0070:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00e7 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00e7 }
            throw r0     // Catch:{ all -> 0x00e7 }
        L_0x0075:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x007d:
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$6
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$3
            kotlin.jvm.internal.Ref$IntRef r11 = (kotlin.jvm.internal.Ref.IntRef) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            java.lang.Object r13 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r13 = (kotlinx.coroutines.channels.SendChannel) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00e7 }
            if (r15 != 0) goto L_0x00ad
            r16 = r10
            r10 = r2
            r2 = r9
            r9 = r16
            goto L_0x0161
        L_0x00ad:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00e7 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00e7 }
            throw r0     // Catch:{ all -> 0x00e7 }
        L_0x00b2:
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$6
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$3
            kotlin.jvm.internal.Ref$IntRef r11 = (kotlin.jvm.internal.Ref.IntRef) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            java.lang.Object r13 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r13 = (kotlinx.coroutines.channels.SendChannel) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00e7 }
            if (r15 != 0) goto L_0x00e2
            r16 = r10
            r10 = r2
            r2 = r9
            r9 = r16
            goto L_0x013e
        L_0x00e2:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00e7 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00e7 }
            throw r0     // Catch:{ all -> 0x00e7 }
        L_0x00e7:
            r0 = move-exception
            r1 = r0
            r2 = r9
            goto L_0x01ab
        L_0x00ec:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x01b8
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r7 = r20.iterator()     // Catch:{ all -> 0x01a7 }
            r8 = r22
            r13 = r0
            r9 = r1
            r10 = r2
            r12 = r3
            r11 = r7
            r0 = r20
            r1 = r0
            r2 = r1
            r3 = r2
            r7 = r21
        L_0x010d:
            r9.L$0 = r0     // Catch:{ all -> 0x01a5 }
            r9.L$1 = r7     // Catch:{ all -> 0x01a5 }
            r9.L$2 = r8     // Catch:{ all -> 0x01a5 }
            r9.L$3 = r13     // Catch:{ all -> 0x01a5 }
            r9.L$4 = r1     // Catch:{ all -> 0x01a5 }
            r9.L$5 = r2     // Catch:{ all -> 0x01a5 }
            r9.L$6 = r12     // Catch:{ all -> 0x01a5 }
            r9.L$7 = r3     // Catch:{ all -> 0x01a5 }
            r9.L$8 = r11     // Catch:{ all -> 0x01a5 }
            r9.label = r6     // Catch:{ all -> 0x01a5 }
            java.lang.Object r14 = r11.hasNext(r9)     // Catch:{ all -> 0x01a5 }
            if (r14 != r10) goto L_0x0128
            return r10
        L_0x0128:
            r16 = r14
            r14 = r0
            r0 = r16
            r17 = r9
            r9 = r1
            r1 = r17
            r18 = r7
            r7 = r3
            r3 = r11
            r11 = r13
            r13 = r18
            r19 = r12
            r12 = r8
            r8 = r19
        L_0x013e:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01a5 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01a5 }
            if (r0 == 0) goto L_0x0199
            r1.L$0 = r14     // Catch:{ all -> 0x01a5 }
            r1.L$1 = r13     // Catch:{ all -> 0x01a5 }
            r1.L$2 = r12     // Catch:{ all -> 0x01a5 }
            r1.L$3 = r11     // Catch:{ all -> 0x01a5 }
            r1.L$4 = r9     // Catch:{ all -> 0x01a5 }
            r1.L$5 = r2     // Catch:{ all -> 0x01a5 }
            r1.L$6 = r8     // Catch:{ all -> 0x01a5 }
            r1.L$7 = r7     // Catch:{ all -> 0x01a5 }
            r1.L$8 = r3     // Catch:{ all -> 0x01a5 }
            r1.label = r5     // Catch:{ all -> 0x01a5 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x01a5 }
            if (r0 != r10) goto L_0x0161
            return r10
        L_0x0161:
            int r15 = r11.element     // Catch:{ all -> 0x01a5 }
            int r5 = r15 + 1
            r11.element = r5     // Catch:{ all -> 0x01a5 }
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r15)     // Catch:{ all -> 0x01a5 }
            java.lang.Object r5 = r12.invoke(r5, r0)     // Catch:{ all -> 0x01a5 }
            r1.L$0 = r14     // Catch:{ all -> 0x01a5 }
            r1.L$1 = r13     // Catch:{ all -> 0x01a5 }
            r1.L$2 = r12     // Catch:{ all -> 0x01a5 }
            r1.L$3 = r11     // Catch:{ all -> 0x01a5 }
            r1.L$4 = r9     // Catch:{ all -> 0x01a5 }
            r1.L$5 = r2     // Catch:{ all -> 0x01a5 }
            r1.L$6 = r8     // Catch:{ all -> 0x01a5 }
            r1.L$7 = r7     // Catch:{ all -> 0x01a5 }
            r1.L$8 = r3     // Catch:{ all -> 0x01a5 }
            r1.L$9 = r0     // Catch:{ all -> 0x01a5 }
            r1.L$10 = r0     // Catch:{ all -> 0x01a5 }
            r1.label = r4     // Catch:{ all -> 0x01a5 }
            java.lang.Object r0 = r13.send(r5, r1)     // Catch:{ all -> 0x01a5 }
            if (r0 != r10) goto L_0x018e
            return r10
        L_0x018e:
            r0 = r14
            r16 = r9
            r9 = r1
            r1 = r16
            goto L_0x0062
        L_0x0196:
            r5 = 2
            goto L_0x010d
        L_0x0199:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x01a5 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            r2.cancel(r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            return r13
        L_0x01a5:
            r0 = move-exception
            goto L_0x01aa
        L_0x01a7:
            r0 = move-exception
            r2 = r20
        L_0x01aa:
            r1 = r0
        L_0x01ab:
            throw r1     // Catch:{ all -> 0x01ac }
        L_0x01ac:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            throw r3
        L_0x01b8:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapIndexedTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ ReceiveChannel mapNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.mapNotNull(receiveChannel, coroutineContext, function2);
    }

    public static final <E, R> ReceiveChannel<R> mapNotNull(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        return ChannelsKt.filterNotNull(ChannelsKt.map(receiveChannel, coroutineContext, function2));
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d0 A[Catch:{ all -> 0x0129 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e9 A[Catch:{ all -> 0x0129 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0114 A[Catch:{ all -> 0x0129 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R, C extends java.util.Collection<? super R>> java.lang.Object mapNotNullTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, C r19, kotlin.jvm.functions.Function1<? super E, ? extends R> r20, kotlin.coroutines.Continuation<? super C> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x009f
            if (r3 == r5) goto L_0x006c
            if (r3 != r4) goto L_0x0064
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$1
            java.util.Collection r11 = (java.util.Collection) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009a }
            if (r13 != 0) goto L_0x005f
            r14 = r9
            r9 = r1
            r1 = r14
            r15 = r10
            r10 = r2
            r2 = r8
            r8 = r7
            r7 = r15
            r16 = r11
            r11 = r3
            r3 = r6
            r6 = r16
            goto L_0x010e
        L_0x005f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009a }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009a }
            throw r0     // Catch:{ all -> 0x009a }
        L_0x0064:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006c:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$1
            java.util.Collection r11 = (java.util.Collection) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009a }
            if (r13 != 0) goto L_0x0095
            r14 = r10
            r10 = r2
            r2 = r8
            r8 = r14
            goto L_0x00e1
        L_0x0095:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009a }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009a }
            throw r0     // Catch:{ all -> 0x009a }
        L_0x009a:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x012f
        L_0x009f:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x013c
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlinx.coroutines.channels.ChannelIterator r3 = r18.iterator()     // Catch:{ all -> 0x012b }
            r6 = r19
            r7 = r20
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r0 = r18
            r1 = r0
            r2 = r1
            r3 = r2
        L_0x00b7:
            r9.L$0 = r0     // Catch:{ all -> 0x0129 }
            r9.L$1 = r6     // Catch:{ all -> 0x0129 }
            r9.L$2 = r7     // Catch:{ all -> 0x0129 }
            r9.L$3 = r1     // Catch:{ all -> 0x0129 }
            r9.L$4 = r2     // Catch:{ all -> 0x0129 }
            r9.L$5 = r8     // Catch:{ all -> 0x0129 }
            r9.L$6 = r3     // Catch:{ all -> 0x0129 }
            r9.L$7 = r11     // Catch:{ all -> 0x0129 }
            r9.label = r5     // Catch:{ all -> 0x0129 }
            java.lang.Object r12 = r11.hasNext(r9)     // Catch:{ all -> 0x0129 }
            if (r12 != r10) goto L_0x00d0
            return r10
        L_0x00d0:
            r14 = r12
            r12 = r0
            r0 = r14
            r15 = r9
            r9 = r1
            r1 = r15
            r16 = r6
            r6 = r3
            r3 = r11
            r11 = r16
            r17 = r8
            r8 = r7
            r7 = r17
        L_0x00e1:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0129 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0129 }
            if (r0 == 0) goto L_0x011d
            r1.L$0 = r12     // Catch:{ all -> 0x0129 }
            r1.L$1 = r11     // Catch:{ all -> 0x0129 }
            r1.L$2 = r8     // Catch:{ all -> 0x0129 }
            r1.L$3 = r9     // Catch:{ all -> 0x0129 }
            r1.L$4 = r2     // Catch:{ all -> 0x0129 }
            r1.L$5 = r7     // Catch:{ all -> 0x0129 }
            r1.L$6 = r6     // Catch:{ all -> 0x0129 }
            r1.L$7 = r3     // Catch:{ all -> 0x0129 }
            r1.label = r4     // Catch:{ all -> 0x0129 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0129 }
            if (r0 != r10) goto L_0x0102
            return r10
        L_0x0102:
            r14 = r9
            r9 = r1
            r1 = r14
            r15 = r11
            r11 = r3
            r3 = r6
            r6 = r15
            r16 = r8
            r8 = r7
            r7 = r16
        L_0x010e:
            java.lang.Object r0 = r7.invoke(r0)     // Catch:{ all -> 0x0129 }
            if (r0 == 0) goto L_0x011b
            boolean r0 = r6.add(r0)     // Catch:{ all -> 0x0129 }
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)     // Catch:{ all -> 0x0129 }
        L_0x011b:
            r0 = r12
            goto L_0x00b7
        L_0x011d:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0129 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            return r11
        L_0x0129:
            r0 = move-exception
            goto L_0x012e
        L_0x012b:
            r0 = move-exception
            r2 = r18
        L_0x012e:
            r1 = r0
        L_0x012f:
            throw r1     // Catch:{ all -> 0x0130 }
        L_0x0130:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x013c:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00fa A[Catch:{ all -> 0x0175 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0112 A[Catch:{ all -> 0x0175 }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0150 A[Catch:{ all -> 0x0175 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R, C extends kotlinx.coroutines.channels.SendChannel<? super R>> java.lang.Object mapNotNullTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, C r19, kotlin.jvm.functions.Function1<? super E, ? extends R> r20, kotlin.coroutines.Continuation<? super C> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$3 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$3 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapNotNullTo$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 3
            r5 = 2
            r6 = 1
            if (r3 == 0) goto L_0x00c9
            if (r3 == r6) goto L_0x0096
            if (r3 == r5) goto L_0x0067
            if (r3 != r4) goto L_0x005f
            java.lang.Object r3 = r1.L$10
            java.lang.Object r3 = r1.L$9
            java.lang.Object r3 = r1.L$8
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r12 = (kotlinx.coroutines.channels.SendChannel) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00c4 }
            if (r14 != 0) goto L_0x005a
            goto L_0x0154
        L_0x005a:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00c4 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00c4 }
            throw r0     // Catch:{ all -> 0x00c4 }
        L_0x005f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0067:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r12 = (kotlinx.coroutines.channels.SendChannel) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00c4 }
            if (r14 != 0) goto L_0x0091
            r15 = r10
            r10 = r2
            r2 = r9
            r9 = r15
            goto L_0x012b
        L_0x0091:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00c4 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00c4 }
            throw r0     // Catch:{ all -> 0x00c4 }
        L_0x0096:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r12 = (kotlinx.coroutines.channels.SendChannel) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00c4 }
            if (r14 != 0) goto L_0x00bf
            r15 = r10
            r10 = r2
            r2 = r9
            r9 = r15
            goto L_0x010a
        L_0x00bf:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00c4 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00c4 }
            throw r0     // Catch:{ all -> 0x00c4 }
        L_0x00c4:
            r0 = move-exception
            r1 = r0
            r2 = r9
            goto L_0x017b
        L_0x00c9:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0188
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlinx.coroutines.channels.ChannelIterator r3 = r18.iterator()     // Catch:{ all -> 0x0177 }
            r7 = r19
            r8 = r20
            r12 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r0 = r18
            r1 = r0
            r2 = r1
            r3 = r2
        L_0x00e1:
            r9.L$0 = r0     // Catch:{ all -> 0x0175 }
            r9.L$1 = r7     // Catch:{ all -> 0x0175 }
            r9.L$2 = r8     // Catch:{ all -> 0x0175 }
            r9.L$3 = r1     // Catch:{ all -> 0x0175 }
            r9.L$4 = r2     // Catch:{ all -> 0x0175 }
            r9.L$5 = r12     // Catch:{ all -> 0x0175 }
            r9.L$6 = r3     // Catch:{ all -> 0x0175 }
            r9.L$7 = r11     // Catch:{ all -> 0x0175 }
            r9.label = r6     // Catch:{ all -> 0x0175 }
            java.lang.Object r13 = r11.hasNext(r9)     // Catch:{ all -> 0x0175 }
            if (r13 != r10) goto L_0x00fa
            return r10
        L_0x00fa:
            r15 = r13
            r13 = r0
            r0 = r15
            r16 = r9
            r9 = r1
            r1 = r16
            r17 = r7
            r7 = r3
            r3 = r11
            r11 = r8
            r8 = r12
            r12 = r17
        L_0x010a:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0175 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0175 }
            if (r0 == 0) goto L_0x0169
            r1.L$0 = r13     // Catch:{ all -> 0x0175 }
            r1.L$1 = r12     // Catch:{ all -> 0x0175 }
            r1.L$2 = r11     // Catch:{ all -> 0x0175 }
            r1.L$3 = r9     // Catch:{ all -> 0x0175 }
            r1.L$4 = r2     // Catch:{ all -> 0x0175 }
            r1.L$5 = r8     // Catch:{ all -> 0x0175 }
            r1.L$6 = r7     // Catch:{ all -> 0x0175 }
            r1.L$7 = r3     // Catch:{ all -> 0x0175 }
            r1.label = r5     // Catch:{ all -> 0x0175 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0175 }
            if (r0 != r10) goto L_0x012b
            return r10
        L_0x012b:
            java.lang.Object r14 = r11.invoke(r0)     // Catch:{ all -> 0x0175 }
            if (r14 == 0) goto L_0x0164
            r1.L$0 = r13     // Catch:{ all -> 0x0175 }
            r1.L$1 = r12     // Catch:{ all -> 0x0175 }
            r1.L$2 = r11     // Catch:{ all -> 0x0175 }
            r1.L$3 = r9     // Catch:{ all -> 0x0175 }
            r1.L$4 = r2     // Catch:{ all -> 0x0175 }
            r1.L$5 = r8     // Catch:{ all -> 0x0175 }
            r1.L$6 = r7     // Catch:{ all -> 0x0175 }
            r1.L$7 = r3     // Catch:{ all -> 0x0175 }
            r1.L$8 = r0     // Catch:{ all -> 0x0175 }
            r1.L$9 = r0     // Catch:{ all -> 0x0175 }
            r1.L$10 = r14     // Catch:{ all -> 0x0175 }
            r1.label = r4     // Catch:{ all -> 0x0175 }
            java.lang.Object r0 = r12.send(r14, r1)     // Catch:{ all -> 0x0175 }
            if (r0 != r10) goto L_0x0150
            return r10
        L_0x0150:
            r15 = r9
            r9 = r2
            r2 = r10
            r10 = r15
        L_0x0154:
            r0 = r13
            r15 = r9
            r9 = r1
            r1 = r10
            r10 = r2
            r2 = r15
        L_0x015a:
            r16 = r11
            r11 = r3
            r3 = r7
            r7 = r12
            r12 = r8
            r8 = r16
            goto L_0x00e1
        L_0x0164:
            r0 = r13
            r15 = r9
            r9 = r1
            r1 = r15
            goto L_0x015a
        L_0x0169:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0175 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            r2.cancel(r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            return r12
        L_0x0175:
            r0 = move-exception
            goto L_0x017a
        L_0x0177:
            r0 = move-exception
            r2 = r18
        L_0x017a:
            r1 = r0
        L_0x017b:
            throw r1     // Catch:{ all -> 0x017c }
        L_0x017c:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            throw r3
        L_0x0188:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapNotNullTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d0 A[Catch:{ all -> 0x0123 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e9 A[Catch:{ all -> 0x0123 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R, C extends java.util.Collection<? super R>> java.lang.Object mapTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, C r19, kotlin.jvm.functions.Function1<? super E, ? extends R> r20, kotlin.coroutines.Continuation<? super C> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x009f
            if (r3 == r5) goto L_0x006c
            if (r3 != r4) goto L_0x0064
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$1
            java.util.Collection r11 = (java.util.Collection) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009a }
            if (r13 != 0) goto L_0x005f
            r14 = r9
            r9 = r1
            r1 = r14
            r15 = r10
            r10 = r2
            r2 = r8
            r8 = r7
            r7 = r15
            r16 = r11
            r11 = r3
            r3 = r6
            r6 = r16
            goto L_0x010e
        L_0x005f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009a }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009a }
            throw r0     // Catch:{ all -> 0x009a }
        L_0x0064:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006c:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r1.L$1
            java.util.Collection r11 = (java.util.Collection) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009a }
            if (r13 != 0) goto L_0x0095
            r14 = r10
            r10 = r2
            r2 = r8
            r8 = r14
            goto L_0x00e1
        L_0x0095:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009a }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009a }
            throw r0     // Catch:{ all -> 0x009a }
        L_0x009a:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x0129
        L_0x009f:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0136
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlinx.coroutines.channels.ChannelIterator r3 = r18.iterator()     // Catch:{ all -> 0x0125 }
            r6 = r19
            r7 = r20
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r0 = r18
            r1 = r0
            r2 = r1
            r3 = r2
        L_0x00b7:
            r9.L$0 = r0     // Catch:{ all -> 0x0123 }
            r9.L$1 = r6     // Catch:{ all -> 0x0123 }
            r9.L$2 = r7     // Catch:{ all -> 0x0123 }
            r9.L$3 = r1     // Catch:{ all -> 0x0123 }
            r9.L$4 = r2     // Catch:{ all -> 0x0123 }
            r9.L$5 = r8     // Catch:{ all -> 0x0123 }
            r9.L$6 = r3     // Catch:{ all -> 0x0123 }
            r9.L$7 = r11     // Catch:{ all -> 0x0123 }
            r9.label = r5     // Catch:{ all -> 0x0123 }
            java.lang.Object r12 = r11.hasNext(r9)     // Catch:{ all -> 0x0123 }
            if (r12 != r10) goto L_0x00d0
            return r10
        L_0x00d0:
            r14 = r12
            r12 = r0
            r0 = r14
            r15 = r9
            r9 = r1
            r1 = r15
            r16 = r6
            r6 = r3
            r3 = r11
            r11 = r16
            r17 = r8
            r8 = r7
            r7 = r17
        L_0x00e1:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0123 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0123 }
            if (r0 == 0) goto L_0x0117
            r1.L$0 = r12     // Catch:{ all -> 0x0123 }
            r1.L$1 = r11     // Catch:{ all -> 0x0123 }
            r1.L$2 = r8     // Catch:{ all -> 0x0123 }
            r1.L$3 = r9     // Catch:{ all -> 0x0123 }
            r1.L$4 = r2     // Catch:{ all -> 0x0123 }
            r1.L$5 = r7     // Catch:{ all -> 0x0123 }
            r1.L$6 = r6     // Catch:{ all -> 0x0123 }
            r1.L$7 = r3     // Catch:{ all -> 0x0123 }
            r1.label = r4     // Catch:{ all -> 0x0123 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0123 }
            if (r0 != r10) goto L_0x0102
            return r10
        L_0x0102:
            r14 = r9
            r9 = r1
            r1 = r14
            r15 = r11
            r11 = r3
            r3 = r6
            r6 = r15
            r16 = r8
            r8 = r7
            r7 = r16
        L_0x010e:
            java.lang.Object r0 = r7.invoke(r0)     // Catch:{ all -> 0x0123 }
            r6.add(r0)     // Catch:{ all -> 0x0123 }
            r0 = r12
            goto L_0x00b7
        L_0x0117:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0123 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            return r11
        L_0x0123:
            r0 = move-exception
            goto L_0x0128
        L_0x0125:
            r0 = move-exception
            r2 = r18
        L_0x0128:
            r1 = r0
        L_0x0129:
            throw r1     // Catch:{ all -> 0x012a }
        L_0x012a:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x0136:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapTo(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0105 A[Catch:{ all -> 0x016a }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0106 A[Catch:{ all -> 0x016a }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x011e A[Catch:{ all -> 0x016a }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0158 A[Catch:{ all -> 0x016a }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R, C extends kotlinx.coroutines.channels.SendChannel<? super R>> java.lang.Object mapTo(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, C r19, kotlin.jvm.functions.Function1<? super E, ? extends R> r20, kotlin.coroutines.Continuation<? super C> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$3 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$3 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapTo$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 3
            r5 = 2
            r6 = 1
            if (r3 == 0) goto L_0x00d5
            if (r3 == r6) goto L_0x00a2
            if (r3 == r5) goto L_0x0073
            if (r3 != r4) goto L_0x006b
            java.lang.Object r3 = r1.L$9
            java.lang.Object r3 = r1.L$8
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r12 = (kotlinx.coroutines.channels.SendChannel) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00d0 }
            if (r14 != 0) goto L_0x0066
            r0 = r13
            r15 = r9
            r9 = r1
            r1 = r10
            r10 = r2
            r2 = r15
        L_0x005c:
            r16 = r11
            r11 = r3
            r3 = r7
            r7 = r12
            r12 = r8
            r8 = r16
            goto L_0x00ed
        L_0x0066:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00d0 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00d0 }
            throw r0     // Catch:{ all -> 0x00d0 }
        L_0x006b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0073:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r12 = (kotlinx.coroutines.channels.SendChannel) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00d0 }
            if (r14 != 0) goto L_0x009d
            r15 = r10
            r10 = r2
            r2 = r9
            r9 = r15
            goto L_0x0137
        L_0x009d:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00d0 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00d0 }
            throw r0     // Catch:{ all -> 0x00d0 }
        L_0x00a2:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r7 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$5
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$1
            kotlinx.coroutines.channels.SendChannel r12 = (kotlinx.coroutines.channels.SendChannel) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00d0 }
            if (r14 != 0) goto L_0x00cb
            r15 = r10
            r10 = r2
            r2 = r9
            r9 = r15
            goto L_0x0116
        L_0x00cb:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00d0 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00d0 }
            throw r0     // Catch:{ all -> 0x00d0 }
        L_0x00d0:
            r0 = move-exception
            r1 = r0
            r2 = r9
            goto L_0x0170
        L_0x00d5:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x017d
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlinx.coroutines.channels.ChannelIterator r3 = r18.iterator()     // Catch:{ all -> 0x016c }
            r7 = r19
            r8 = r20
            r12 = r0
            r9 = r1
            r10 = r2
            r11 = r3
            r0 = r18
            r1 = r0
            r2 = r1
            r3 = r2
        L_0x00ed:
            r9.L$0 = r0     // Catch:{ all -> 0x016a }
            r9.L$1 = r7     // Catch:{ all -> 0x016a }
            r9.L$2 = r8     // Catch:{ all -> 0x016a }
            r9.L$3 = r1     // Catch:{ all -> 0x016a }
            r9.L$4 = r2     // Catch:{ all -> 0x016a }
            r9.L$5 = r12     // Catch:{ all -> 0x016a }
            r9.L$6 = r3     // Catch:{ all -> 0x016a }
            r9.L$7 = r11     // Catch:{ all -> 0x016a }
            r9.label = r6     // Catch:{ all -> 0x016a }
            java.lang.Object r13 = r11.hasNext(r9)     // Catch:{ all -> 0x016a }
            if (r13 != r10) goto L_0x0106
            return r10
        L_0x0106:
            r15 = r13
            r13 = r0
            r0 = r15
            r16 = r9
            r9 = r1
            r1 = r16
            r17 = r7
            r7 = r3
            r3 = r11
            r11 = r8
            r8 = r12
            r12 = r17
        L_0x0116:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x016a }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x016a }
            if (r0 == 0) goto L_0x015e
            r1.L$0 = r13     // Catch:{ all -> 0x016a }
            r1.L$1 = r12     // Catch:{ all -> 0x016a }
            r1.L$2 = r11     // Catch:{ all -> 0x016a }
            r1.L$3 = r9     // Catch:{ all -> 0x016a }
            r1.L$4 = r2     // Catch:{ all -> 0x016a }
            r1.L$5 = r8     // Catch:{ all -> 0x016a }
            r1.L$6 = r7     // Catch:{ all -> 0x016a }
            r1.L$7 = r3     // Catch:{ all -> 0x016a }
            r1.label = r5     // Catch:{ all -> 0x016a }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x016a }
            if (r0 != r10) goto L_0x0137
            return r10
        L_0x0137:
            java.lang.Object r14 = r11.invoke(r0)     // Catch:{ all -> 0x016a }
            r1.L$0 = r13     // Catch:{ all -> 0x016a }
            r1.L$1 = r12     // Catch:{ all -> 0x016a }
            r1.L$2 = r11     // Catch:{ all -> 0x016a }
            r1.L$3 = r9     // Catch:{ all -> 0x016a }
            r1.L$4 = r2     // Catch:{ all -> 0x016a }
            r1.L$5 = r8     // Catch:{ all -> 0x016a }
            r1.L$6 = r7     // Catch:{ all -> 0x016a }
            r1.L$7 = r3     // Catch:{ all -> 0x016a }
            r1.L$8 = r0     // Catch:{ all -> 0x016a }
            r1.L$9 = r0     // Catch:{ all -> 0x016a }
            r1.label = r4     // Catch:{ all -> 0x016a }
            java.lang.Object r0 = r12.send(r14, r1)     // Catch:{ all -> 0x016a }
            if (r0 != r10) goto L_0x0158
            return r10
        L_0x0158:
            r0 = r13
            r15 = r9
            r9 = r1
            r1 = r15
            goto L_0x005c
        L_0x015e:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x016a }
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            r2.cancel(r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            return r12
        L_0x016a:
            r0 = move-exception
            goto L_0x016f
        L_0x016c:
            r0 = move-exception
            r2 = r18
        L_0x016f:
            r1 = r0
        L_0x0170:
            throw r1     // Catch:{ all -> 0x0171 }
        L_0x0171:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            throw r3
        L_0x017d:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapTo(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ ReceiveChannel withIndex$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.withIndex(receiveChannel, coroutineContext);
    }

    public static final <E> ReceiveChannel<IndexedValue<E>> withIndex(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$withIndex$1(receiveChannel, (Continuation) null), 2, (Object) null);
    }

    public static final <E> ReceiveChannel<E> distinct(ReceiveChannel<? extends E> receiveChannel) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        return distinctBy$default(receiveChannel, (CoroutineContext) null, new ChannelsKt__Channels_commonKt$distinct$1((Continuation) null), 1, (Object) null);
    }

    public static /* synthetic */ ReceiveChannel distinctBy$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.distinctBy(receiveChannel, coroutineContext, function2);
    }

    public static final <E, K> ReceiveChannel<E> distinctBy(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super K>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "selector");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumes(receiveChannel), new ChannelsKt__Channels_commonKt$distinctBy$1(receiveChannel, function2, (Continuation) null), 2, (Object) null);
    }

    public static final <E> Object toMutableSet(ReceiveChannel<? extends E> receiveChannel, Continuation<? super Set<E>> continuation) {
        return ChannelsKt.toCollection(receiveChannel, new LinkedHashSet(), continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ba A[Catch:{ all -> 0x0118 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ca A[Catch:{ all -> 0x0118 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object all(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r14, kotlin.coroutines.Continuation<? super java.lang.Boolean> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$all$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$all$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$all$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$all$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$all$1
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0090
            if (r2 == r4) goto L_0x0062
            if (r2 != r3) goto L_0x005a
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008c }
            if (r9 != 0) goto L_0x0055
            r10 = r8
            r8 = r13
            r13 = r10
        L_0x004c:
            r11 = r0
            r0 = r14
            r14 = r6
            r6 = r11
            r12 = r7
            r7 = r1
            r1 = r12
            goto L_0x00e8
        L_0x0055:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x008c }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x008c }
            throw r13     // Catch:{ all -> 0x008c }
        L_0x005a:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0062:
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008c }
            if (r9 != 0) goto L_0x0087
            r10 = r8
            r8 = r15
            r15 = r5
            r5 = r10
            goto L_0x00c2
        L_0x0087:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x008c }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x008c }
            throw r13     // Catch:{ all -> 0x008c }
        L_0x008c:
            r13 = move-exception
            r15 = r5
            goto L_0x011d
        L_0x0090:
            boolean r2 = r15 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x0129
            r15 = 0
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            kotlinx.coroutines.channels.ChannelIterator r2 = r13.iterator()     // Catch:{ all -> 0x011a }
            r5 = r0
            r6 = r1
            r7 = r2
            r0 = r13
            r1 = r14
            r2 = r15
            r14 = r0
            r15 = r14
        L_0x00a3:
            r5.L$0 = r13     // Catch:{ all -> 0x0118 }
            r5.L$1 = r1     // Catch:{ all -> 0x0118 }
            r5.L$2 = r14     // Catch:{ all -> 0x0118 }
            r5.L$3 = r15     // Catch:{ all -> 0x0118 }
            r5.L$4 = r2     // Catch:{ all -> 0x0118 }
            r5.L$5 = r0     // Catch:{ all -> 0x0118 }
            r5.L$6 = r7     // Catch:{ all -> 0x0118 }
            r5.label = r4     // Catch:{ all -> 0x0118 }
            java.lang.Object r8 = r7.hasNext(r5)     // Catch:{ all -> 0x0118 }
            if (r8 != r6) goto L_0x00ba
            return r6
        L_0x00ba:
            r10 = r5
            r5 = r13
            r13 = r7
            r7 = r1
            r1 = r6
            r6 = r14
            r14 = r0
            r0 = r10
        L_0x00c2:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x0118 }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x0118 }
            if (r8 == 0) goto L_0x0108
            r0.L$0 = r5     // Catch:{ all -> 0x0118 }
            r0.L$1 = r7     // Catch:{ all -> 0x0118 }
            r0.L$2 = r6     // Catch:{ all -> 0x0118 }
            r0.L$3 = r15     // Catch:{ all -> 0x0118 }
            r0.L$4 = r2     // Catch:{ all -> 0x0118 }
            r0.L$5 = r14     // Catch:{ all -> 0x0118 }
            r0.L$6 = r13     // Catch:{ all -> 0x0118 }
            r0.label = r3     // Catch:{ all -> 0x0118 }
            java.lang.Object r8 = r13.next(r0)     // Catch:{ all -> 0x0118 }
            if (r8 != r1) goto L_0x00e1
            return r1
        L_0x00e1:
            r10 = r8
            r8 = r13
            r13 = r5
            r5 = r15
            r15 = r10
            goto L_0x004c
        L_0x00e8:
            java.lang.Object r15 = r1.invoke(r15)     // Catch:{ all -> 0x008c }
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ all -> 0x008c }
            boolean r15 = r15.booleanValue()     // Catch:{ all -> 0x008c }
            if (r15 != 0) goto L_0x0103
            r13 = 0
            java.lang.Boolean r13 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r13)     // Catch:{ all -> 0x008c }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            r5.cancel(r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r13
        L_0x0103:
            r15 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            goto L_0x00a3
        L_0x0108:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0118 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            r15.cancel(r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            java.lang.Boolean r13 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r13
        L_0x0118:
            r13 = move-exception
            goto L_0x011d
        L_0x011a:
            r14 = move-exception
            r15 = r13
            r13 = r14
        L_0x011d:
            throw r13     // Catch:{ all -> 0x011e }
        L_0x011e:
            r14 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            r15.cancel(r13)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r14
        L_0x0129:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15
            java.lang.Throwable r13 = r15.exception
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.all(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object any(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r4, kotlin.coroutines.Continuation<? super java.lang.Boolean> r5) {
        /*
            boolean r0 = r5 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$1
            r0.<init>(r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x004d
            if (r2 != r3) goto L_0x0045
            java.lang.Object r4 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r4 = r0.L$2
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            java.lang.Object r1 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            boolean r0 = r5 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0043 }
            if (r0 != 0) goto L_0x003e
            r0 = r5
            r5 = r4
            r4 = r1
            goto L_0x0069
        L_0x003e:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5     // Catch:{ all -> 0x0043 }
            java.lang.Throwable r4 = r5.exception     // Catch:{ all -> 0x0043 }
            throw r4     // Catch:{ all -> 0x0043 }
        L_0x0043:
            r4 = move-exception
            goto L_0x0070
        L_0x0045:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x004d:
            boolean r2 = r5 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x0076
            r5 = 0
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            kotlinx.coroutines.channels.ChannelIterator r2 = r4.iterator()     // Catch:{ all -> 0x006d }
            r0.L$0 = r4     // Catch:{ all -> 0x006d }
            r0.L$1 = r4     // Catch:{ all -> 0x006d }
            r0.L$2 = r5     // Catch:{ all -> 0x006d }
            r0.L$3 = r4     // Catch:{ all -> 0x006d }
            r0.label = r3     // Catch:{ all -> 0x006d }
            java.lang.Object r0 = r2.hasNext(r0)     // Catch:{ all -> 0x006d }
            if (r0 != r1) goto L_0x0069
            return r1
        L_0x0069:
            r4.cancel(r5)
            return r0
        L_0x006d:
            r5 = move-exception
            r1 = r4
            r4 = r5
        L_0x0070:
            throw r4     // Catch:{ all -> 0x0071 }
        L_0x0071:
            r5 = move-exception
            r1.cancel(r4)
            throw r5
        L_0x0076:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r4 = r5.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.any(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ba A[Catch:{ all -> 0x0118 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ca A[Catch:{ all -> 0x0118 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object any(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r14, kotlin.coroutines.Continuation<? super java.lang.Boolean> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$3
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$any$3
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0090
            if (r2 == r4) goto L_0x0062
            if (r2 != r3) goto L_0x005a
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008c }
            if (r9 != 0) goto L_0x0055
            r10 = r8
            r8 = r13
            r13 = r10
        L_0x004c:
            r11 = r0
            r0 = r14
            r14 = r6
            r6 = r11
            r12 = r7
            r7 = r1
            r1 = r12
            goto L_0x00e8
        L_0x0055:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x008c }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x008c }
            throw r13     // Catch:{ all -> 0x008c }
        L_0x005a:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0062:
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008c }
            if (r9 != 0) goto L_0x0087
            r10 = r8
            r8 = r15
            r15 = r5
            r5 = r10
            goto L_0x00c2
        L_0x0087:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x008c }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x008c }
            throw r13     // Catch:{ all -> 0x008c }
        L_0x008c:
            r13 = move-exception
            r15 = r5
            goto L_0x011d
        L_0x0090:
            boolean r2 = r15 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x0129
            r15 = 0
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            kotlinx.coroutines.channels.ChannelIterator r2 = r13.iterator()     // Catch:{ all -> 0x011a }
            r5 = r0
            r6 = r1
            r7 = r2
            r0 = r13
            r1 = r14
            r2 = r15
            r14 = r0
            r15 = r14
        L_0x00a3:
            r5.L$0 = r13     // Catch:{ all -> 0x0118 }
            r5.L$1 = r1     // Catch:{ all -> 0x0118 }
            r5.L$2 = r14     // Catch:{ all -> 0x0118 }
            r5.L$3 = r15     // Catch:{ all -> 0x0118 }
            r5.L$4 = r2     // Catch:{ all -> 0x0118 }
            r5.L$5 = r0     // Catch:{ all -> 0x0118 }
            r5.L$6 = r7     // Catch:{ all -> 0x0118 }
            r5.label = r4     // Catch:{ all -> 0x0118 }
            java.lang.Object r8 = r7.hasNext(r5)     // Catch:{ all -> 0x0118 }
            if (r8 != r6) goto L_0x00ba
            return r6
        L_0x00ba:
            r10 = r5
            r5 = r13
            r13 = r7
            r7 = r1
            r1 = r6
            r6 = r14
            r14 = r0
            r0 = r10
        L_0x00c2:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x0118 }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x0118 }
            if (r8 == 0) goto L_0x0107
            r0.L$0 = r5     // Catch:{ all -> 0x0118 }
            r0.L$1 = r7     // Catch:{ all -> 0x0118 }
            r0.L$2 = r6     // Catch:{ all -> 0x0118 }
            r0.L$3 = r15     // Catch:{ all -> 0x0118 }
            r0.L$4 = r2     // Catch:{ all -> 0x0118 }
            r0.L$5 = r14     // Catch:{ all -> 0x0118 }
            r0.L$6 = r13     // Catch:{ all -> 0x0118 }
            r0.label = r3     // Catch:{ all -> 0x0118 }
            java.lang.Object r8 = r13.next(r0)     // Catch:{ all -> 0x0118 }
            if (r8 != r1) goto L_0x00e1
            return r1
        L_0x00e1:
            r10 = r8
            r8 = r13
            r13 = r5
            r5 = r15
            r15 = r10
            goto L_0x004c
        L_0x00e8:
            java.lang.Object r15 = r1.invoke(r15)     // Catch:{ all -> 0x008c }
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ all -> 0x008c }
            boolean r15 = r15.booleanValue()     // Catch:{ all -> 0x008c }
            if (r15 == 0) goto L_0x0102
            java.lang.Boolean r13 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)     // Catch:{ all -> 0x008c }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            r5.cancel(r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r13
        L_0x0102:
            r15 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            goto L_0x00a3
        L_0x0107:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0118 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            r15.cancel(r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            r13 = 0
            java.lang.Boolean r13 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r13)
            return r13
        L_0x0118:
            r13 = move-exception
            goto L_0x011d
        L_0x011a:
            r14 = move-exception
            r15 = r13
            r13 = r14
        L_0x011d:
            throw r13     // Catch:{ all -> 0x011e }
        L_0x011e:
            r14 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            r15.cancel(r13)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r14
        L_0x0129:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15
            java.lang.Throwable r13 = r15.exception
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.any(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c1 A[Catch:{ all -> 0x00fe }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c2 A[Catch:{ all -> 0x00fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d2 A[Catch:{ all -> 0x00fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object count(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, kotlin.coroutines.Continuation<? super java.lang.Integer> r13) {
        /*
            boolean r0 = r13 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$1
            r0.<init>(r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0090
            if (r2 == r4) goto L_0x0061
            if (r2 != r3) goto L_0x0059
            java.lang.Object r12 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r2 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$4
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.internal.Ref$IntRef r8 = (kotlin.jvm.internal.Ref.IntRef) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            boolean r10 = r13 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008c }
            if (r10 != 0) goto L_0x0054
            r13 = r7
            r7 = r5
            r5 = r0
            r0 = r6
        L_0x004d:
            r6 = r1
            r1 = r2
            r2 = r8
            r8 = r12
            r12 = r9
            goto L_0x00ec
        L_0x0054:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13     // Catch:{ all -> 0x008c }
            java.lang.Throwable r12 = r13.exception     // Catch:{ all -> 0x008c }
            throw r12     // Catch:{ all -> 0x008c }
        L_0x0059:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0061:
            java.lang.Object r12 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r2 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r5 = r0.L$4
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.internal.Ref$IntRef r8 = (kotlin.jvm.internal.Ref.IntRef) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            boolean r10 = r13 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008c }
            if (r10 != 0) goto L_0x0087
            r11 = r5
            r5 = r0
            r0 = r6
            r6 = r7
            r7 = r11
            goto L_0x00ca
        L_0x0087:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13     // Catch:{ all -> 0x008c }
            java.lang.Throwable r12 = r13.exception     // Catch:{ all -> 0x008c }
            throw r12     // Catch:{ all -> 0x008c }
        L_0x008c:
            r12 = move-exception
            r0 = r6
            goto L_0x0103
        L_0x0090:
            boolean r2 = r13 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x0109
            kotlin.jvm.internal.Ref$IntRef r13 = new kotlin.jvm.internal.Ref$IntRef
            r13.<init>()
            r2 = 0
            r13.element = r2
            r2 = 0
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            kotlinx.coroutines.channels.ChannelIterator r5 = r12.iterator()     // Catch:{ all -> 0x0100 }
            r6 = r1
            r7 = r2
            r8 = r5
            r1 = r12
            r2 = r13
            r5 = r0
            r13 = r1
            r0 = r13
        L_0x00ab:
            r5.L$0 = r12     // Catch:{ all -> 0x00fe }
            r5.L$1 = r2     // Catch:{ all -> 0x00fe }
            r5.L$2 = r13     // Catch:{ all -> 0x00fe }
            r5.L$3 = r0     // Catch:{ all -> 0x00fe }
            r5.L$4 = r7     // Catch:{ all -> 0x00fe }
            r5.L$5 = r1     // Catch:{ all -> 0x00fe }
            r5.L$6 = r8     // Catch:{ all -> 0x00fe }
            r5.label = r4     // Catch:{ all -> 0x00fe }
            java.lang.Object r9 = r8.hasNext(r5)     // Catch:{ all -> 0x00fe }
            if (r9 != r6) goto L_0x00c2
            return r6
        L_0x00c2:
            r11 = r9
            r9 = r12
            r12 = r8
            r8 = r2
            r2 = r1
            r1 = r6
            r6 = r13
            r13 = r11
        L_0x00ca:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ all -> 0x00fe }
            boolean r13 = r13.booleanValue()     // Catch:{ all -> 0x00fe }
            if (r13 == 0) goto L_0x00f2
            r5.L$0 = r9     // Catch:{ all -> 0x00fe }
            r5.L$1 = r8     // Catch:{ all -> 0x00fe }
            r5.L$2 = r6     // Catch:{ all -> 0x00fe }
            r5.L$3 = r0     // Catch:{ all -> 0x00fe }
            r5.L$4 = r7     // Catch:{ all -> 0x00fe }
            r5.L$5 = r2     // Catch:{ all -> 0x00fe }
            r5.L$6 = r12     // Catch:{ all -> 0x00fe }
            r5.label = r3     // Catch:{ all -> 0x00fe }
            java.lang.Object r13 = r12.next(r5)     // Catch:{ all -> 0x00fe }
            if (r13 != r1) goto L_0x00e9
            return r1
        L_0x00e9:
            r13 = r6
            goto L_0x004d
        L_0x00ec:
            int r9 = r2.element     // Catch:{ all -> 0x00fe }
            int r9 = r9 + r4
            r2.element = r9     // Catch:{ all -> 0x00fe }
            goto L_0x00ab
        L_0x00f2:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00fe }
            r0.cancel(r7)
            int r12 = r8.element
            java.lang.Integer r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r12)
            return r12
        L_0x00fe:
            r12 = move-exception
            goto L_0x0103
        L_0x0100:
            r13 = move-exception
            r0 = r12
            r12 = r13
        L_0x0103:
            throw r12     // Catch:{ all -> 0x0104 }
        L_0x0104:
            r13 = move-exception
            r0.cancel(r12)
            throw r13
        L_0x0109:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r12 = r13.exception
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.count(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d7 A[Catch:{ all -> 0x0134 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d8 A[Catch:{ all -> 0x0134 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f1 A[Catch:{ all -> 0x0134 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x011b A[Catch:{ all -> 0x0134 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object count(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r19, kotlin.coroutines.Continuation<? super java.lang.Integer> r20) {
        /*
            r0 = r20
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$3 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$3 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$count$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00a0
            if (r3 == r5) goto L_0x006c
            if (r3 != r4) goto L_0x0064
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009b }
            if (r13 != 0) goto L_0x005f
            r14 = r8
            r8 = r1
            r1 = r9
            r9 = r2
            r2 = r14
        L_0x0054:
            r15 = r11
            r11 = r3
            r3 = r6
            r6 = r15
            r16 = r10
            r10 = r7
            r7 = r16
            goto L_0x010f
        L_0x005f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009b }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009b }
            throw r0     // Catch:{ all -> 0x009b }
        L_0x0064:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006c:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009b }
            if (r13 != 0) goto L_0x0096
            r14 = r9
            r9 = r2
            r2 = r8
            r8 = r14
            goto L_0x00e9
        L_0x0096:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009b }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009b }
            throw r0     // Catch:{ all -> 0x009b }
        L_0x009b:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x013a
        L_0x00a0:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0147
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r6 = r18.iterator()     // Catch:{ all -> 0x0136 }
            r7 = r0
            r8 = r1
            r9 = r2
            r10 = r3
            r11 = r6
            r0 = r18
            r1 = r0
            r2 = r1
            r3 = r2
            r6 = r19
        L_0x00bf:
            r8.L$0 = r0     // Catch:{ all -> 0x0134 }
            r8.L$1 = r6     // Catch:{ all -> 0x0134 }
            r8.L$2 = r7     // Catch:{ all -> 0x0134 }
            r8.L$3 = r1     // Catch:{ all -> 0x0134 }
            r8.L$4 = r2     // Catch:{ all -> 0x0134 }
            r8.L$5 = r10     // Catch:{ all -> 0x0134 }
            r8.L$6 = r3     // Catch:{ all -> 0x0134 }
            r8.L$7 = r11     // Catch:{ all -> 0x0134 }
            r8.label = r5     // Catch:{ all -> 0x0134 }
            java.lang.Object r12 = r11.hasNext(r8)     // Catch:{ all -> 0x0134 }
            if (r12 != r9) goto L_0x00d8
            return r9
        L_0x00d8:
            r14 = r12
            r12 = r0
            r0 = r14
            r15 = r8
            r8 = r1
            r1 = r15
            r16 = r6
            r6 = r3
            r3 = r11
            r11 = r16
            r17 = r10
            r10 = r7
            r7 = r17
        L_0x00e9:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0134 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0134 }
            if (r0 == 0) goto L_0x0122
            r1.L$0 = r12     // Catch:{ all -> 0x0134 }
            r1.L$1 = r11     // Catch:{ all -> 0x0134 }
            r1.L$2 = r10     // Catch:{ all -> 0x0134 }
            r1.L$3 = r8     // Catch:{ all -> 0x0134 }
            r1.L$4 = r2     // Catch:{ all -> 0x0134 }
            r1.L$5 = r7     // Catch:{ all -> 0x0134 }
            r1.L$6 = r6     // Catch:{ all -> 0x0134 }
            r1.L$7 = r3     // Catch:{ all -> 0x0134 }
            r1.label = r4     // Catch:{ all -> 0x0134 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0134 }
            if (r0 != r9) goto L_0x010a
            return r9
        L_0x010a:
            r14 = r8
            r8 = r1
            r1 = r14
            goto L_0x0054
        L_0x010f:
            java.lang.Object r0 = r6.invoke(r0)     // Catch:{ all -> 0x0134 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0134 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0134 }
            if (r0 == 0) goto L_0x0120
            int r0 = r7.element     // Catch:{ all -> 0x0134 }
            int r0 = r0 + r5
            r7.element = r0     // Catch:{ all -> 0x0134 }
        L_0x0120:
            r0 = r12
            goto L_0x00bf
        L_0x0122:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0134 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            int r0 = r10.element
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)
            return r0
        L_0x0134:
            r0 = move-exception
            goto L_0x0139
        L_0x0136:
            r0 = move-exception
            r2 = r18
        L_0x0139:
            r1 = r0
        L_0x013a:
            throw r1     // Catch:{ all -> 0x013b }
        L_0x013b:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x0147:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.count(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e6 A[Catch:{ all -> 0x0142 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0101 A[Catch:{ all -> 0x0142 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R> java.lang.Object fold(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r19, R r20, kotlin.jvm.functions.Function2<? super R, ? super E, ? extends R> r21, kotlin.coroutines.Continuation<? super R> r22) {
        /*
            r0 = r22
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$fold$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$fold$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$fold$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$fold$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$fold$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00aa
            if (r3 == r5) goto L_0x0074
            if (r3 != r4) goto L_0x006c
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            java.lang.Object r12 = r1.L$1
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a5 }
            if (r14 != 0) goto L_0x0067
            r15 = r9
            r9 = r1
            r1 = r15
            r16 = r10
            r10 = r2
            r2 = r8
            r8 = r16
            r17 = r12
            r12 = r3
            r3 = r6
            r6 = r17
            r18 = r11
            r11 = r7
            r7 = r18
            goto L_0x012a
        L_0x0067:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a5 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a5 }
            throw r0     // Catch:{ all -> 0x00a5 }
        L_0x006c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0074:
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            java.lang.Object r12 = r1.L$1
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a5 }
            if (r14 != 0) goto L_0x00a0
            r15 = r10
            r10 = r2
            r2 = r8
            r8 = r15
            goto L_0x00f9
        L_0x00a0:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a5 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a5 }
            throw r0     // Catch:{ all -> 0x00a5 }
        L_0x00a5:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x0148
        L_0x00aa:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0155
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r0.<init>()
            r3 = r20
            r0.element = r3
            r6 = 0
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r7 = r19.iterator()     // Catch:{ all -> 0x0144 }
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = r6
            r12 = r7
            r0 = r19
            r1 = r0
            r2 = r1
            r7 = r21
            r6 = r3
            r3 = r2
        L_0x00cb:
            r9.L$0 = r0     // Catch:{ all -> 0x0142 }
            r9.L$1 = r6     // Catch:{ all -> 0x0142 }
            r9.L$2 = r7     // Catch:{ all -> 0x0142 }
            r9.L$3 = r8     // Catch:{ all -> 0x0142 }
            r9.L$4 = r1     // Catch:{ all -> 0x0142 }
            r9.L$5 = r2     // Catch:{ all -> 0x0142 }
            r9.L$6 = r11     // Catch:{ all -> 0x0142 }
            r9.L$7 = r3     // Catch:{ all -> 0x0142 }
            r9.L$8 = r12     // Catch:{ all -> 0x0142 }
            r9.label = r5     // Catch:{ all -> 0x0142 }
            java.lang.Object r13 = r12.hasNext(r9)     // Catch:{ all -> 0x0142 }
            if (r13 != r10) goto L_0x00e6
            return r10
        L_0x00e6:
            r15 = r13
            r13 = r0
            r0 = r15
            r16 = r9
            r9 = r1
            r1 = r16
            r17 = r6
            r6 = r3
            r3 = r12
            r12 = r17
            r18 = r11
            r11 = r7
            r7 = r18
        L_0x00f9:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0142 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0142 }
            if (r0 == 0) goto L_0x0134
            r1.L$0 = r13     // Catch:{ all -> 0x0142 }
            r1.L$1 = r12     // Catch:{ all -> 0x0142 }
            r1.L$2 = r11     // Catch:{ all -> 0x0142 }
            r1.L$3 = r8     // Catch:{ all -> 0x0142 }
            r1.L$4 = r9     // Catch:{ all -> 0x0142 }
            r1.L$5 = r2     // Catch:{ all -> 0x0142 }
            r1.L$6 = r7     // Catch:{ all -> 0x0142 }
            r1.L$7 = r6     // Catch:{ all -> 0x0142 }
            r1.L$8 = r3     // Catch:{ all -> 0x0142 }
            r1.label = r4     // Catch:{ all -> 0x0142 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0142 }
            if (r0 != r10) goto L_0x011c
            return r10
        L_0x011c:
            r15 = r9
            r9 = r1
            r1 = r15
            r16 = r12
            r12 = r3
            r3 = r6
            r6 = r16
            r17 = r11
            r11 = r7
            r7 = r17
        L_0x012a:
            T r14 = r8.element     // Catch:{ all -> 0x0142 }
            java.lang.Object r0 = r7.invoke(r14, r0)     // Catch:{ all -> 0x0142 }
            r8.element = r0     // Catch:{ all -> 0x0142 }
            r0 = r13
            goto L_0x00cb
        L_0x0134:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0142 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            T r0 = r8.element
            return r0
        L_0x0142:
            r0 = move-exception
            goto L_0x0147
        L_0x0144:
            r0 = move-exception
            r2 = r19
        L_0x0147:
            r1 = r0
        L_0x0148:
            throw r1     // Catch:{ all -> 0x0149 }
        L_0x0149:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x0155:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.fold(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00fe A[Catch:{ all -> 0x016b }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x011b A[Catch:{ all -> 0x016b }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R> java.lang.Object foldIndexed(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r20, R r21, kotlin.jvm.functions.Function3<? super java.lang.Integer, ? super R, ? super E, ? extends R> r22, kotlin.coroutines.Continuation<? super R> r23) {
        /*
            r0 = r23
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$foldIndexed$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$foldIndexed$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$foldIndexed$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$foldIndexed$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$foldIndexed$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00b8
            if (r3 == r5) goto L_0x007b
            if (r3 != r4) goto L_0x0073
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$7
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r1.L$3
            kotlin.jvm.internal.Ref$IntRef r11 = (kotlin.jvm.internal.Ref.IntRef) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function3 r12 = (kotlin.jvm.functions.Function3) r12
            java.lang.Object r13 = r1.L$1
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00b3 }
            if (r15 != 0) goto L_0x006e
            r16 = r9
            r9 = r1
            r1 = r16
            r17 = r10
            r10 = r2
            r2 = r8
            r8 = r11
            r11 = r17
            r18 = r13
            r13 = r3
            r3 = r6
            r6 = r18
            r19 = r12
            r12 = r7
            r7 = r19
            goto L_0x0148
        L_0x006e:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00b3 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00b3 }
            throw r0     // Catch:{ all -> 0x00b3 }
        L_0x0073:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x007b:
            java.lang.Object r3 = r1.L$9
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$8
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$7
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$4
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r1.L$3
            kotlin.jvm.internal.Ref$IntRef r11 = (kotlin.jvm.internal.Ref.IntRef) r11
            java.lang.Object r12 = r1.L$2
            kotlin.jvm.functions.Function3 r12 = (kotlin.jvm.functions.Function3) r12
            java.lang.Object r13 = r1.L$1
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00b3 }
            if (r15 != 0) goto L_0x00ae
            r16 = r10
            r10 = r2
            r2 = r8
            r8 = r11
            r11 = r16
            goto L_0x0113
        L_0x00ae:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00b3 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00b3 }
            throw r0     // Catch:{ all -> 0x00b3 }
        L_0x00b3:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x0171
        L_0x00b8:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x017e
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            kotlin.jvm.internal.Ref$ObjectRef r3 = new kotlin.jvm.internal.Ref$ObjectRef
            r3.<init>()
            r6 = r21
            r3.element = r6
            r7 = 0
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            kotlinx.coroutines.channels.ChannelIterator r8 = r20.iterator()     // Catch:{ all -> 0x016d }
            r9 = r1
            r10 = r2
            r11 = r3
            r12 = r7
            r13 = r8
            r1 = r20
            r2 = r1
            r3 = r2
            r7 = r22
            r8 = r0
            r0 = r3
        L_0x00e1:
            r9.L$0 = r0     // Catch:{ all -> 0x016b }
            r9.L$1 = r6     // Catch:{ all -> 0x016b }
            r9.L$2 = r7     // Catch:{ all -> 0x016b }
            r9.L$3 = r8     // Catch:{ all -> 0x016b }
            r9.L$4 = r11     // Catch:{ all -> 0x016b }
            r9.L$5 = r1     // Catch:{ all -> 0x016b }
            r9.L$6 = r2     // Catch:{ all -> 0x016b }
            r9.L$7 = r12     // Catch:{ all -> 0x016b }
            r9.L$8 = r3     // Catch:{ all -> 0x016b }
            r9.L$9 = r13     // Catch:{ all -> 0x016b }
            r9.label = r5     // Catch:{ all -> 0x016b }
            java.lang.Object r14 = r13.hasNext(r9)     // Catch:{ all -> 0x016b }
            if (r14 != r10) goto L_0x00fe
            return r10
        L_0x00fe:
            r16 = r14
            r14 = r0
            r0 = r16
            r17 = r9
            r9 = r1
            r1 = r17
            r18 = r6
            r6 = r3
            r3 = r13
            r13 = r18
            r19 = r12
            r12 = r7
            r7 = r19
        L_0x0113:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x016b }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x016b }
            if (r0 == 0) goto L_0x015d
            r1.L$0 = r14     // Catch:{ all -> 0x016b }
            r1.L$1 = r13     // Catch:{ all -> 0x016b }
            r1.L$2 = r12     // Catch:{ all -> 0x016b }
            r1.L$3 = r8     // Catch:{ all -> 0x016b }
            r1.L$4 = r11     // Catch:{ all -> 0x016b }
            r1.L$5 = r9     // Catch:{ all -> 0x016b }
            r1.L$6 = r2     // Catch:{ all -> 0x016b }
            r1.L$7 = r7     // Catch:{ all -> 0x016b }
            r1.L$8 = r6     // Catch:{ all -> 0x016b }
            r1.L$9 = r3     // Catch:{ all -> 0x016b }
            r1.label = r4     // Catch:{ all -> 0x016b }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x016b }
            if (r0 != r10) goto L_0x0138
            return r10
        L_0x0138:
            r16 = r9
            r9 = r1
            r1 = r16
            r17 = r13
            r13 = r3
            r3 = r6
            r6 = r17
            r18 = r12
            r12 = r7
            r7 = r18
        L_0x0148:
            int r15 = r8.element     // Catch:{ all -> 0x016b }
            int r4 = r15 + 1
            r8.element = r4     // Catch:{ all -> 0x016b }
            java.lang.Integer r4 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r15)     // Catch:{ all -> 0x016b }
            T r15 = r11.element     // Catch:{ all -> 0x016b }
            java.lang.Object r0 = r7.invoke(r4, r15, r0)     // Catch:{ all -> 0x016b }
            r11.element = r0     // Catch:{ all -> 0x016b }
            r0 = r14
            r4 = 2
            goto L_0x00e1
        L_0x015d:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x016b }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            T r0 = r11.element
            return r0
        L_0x016b:
            r0 = move-exception
            goto L_0x0170
        L_0x016d:
            r0 = move-exception
            r2 = r20
        L_0x0170:
            r1 = r0
        L_0x0171:
            throw r1     // Catch:{ all -> 0x0172 }
        L_0x0172:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x017e:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.foldIndexed(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x012e  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0138 A[SYNTHETIC, Splitter:B:60:0x0138] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x016d A[Catch:{ all -> 0x01c4 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x016e A[Catch:{ all -> 0x01c4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0183 A[Catch:{ all -> 0x01c4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01b2  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R extends java.lang.Comparable<? super R>> java.lang.Object maxBy(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r19, kotlin.jvm.functions.Function1<? super E, ? extends R> r20, kotlin.coroutines.Continuation<? super E> r21) {
        /*
            r1 = r19
            r0 = r21
            boolean r2 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxBy$1
            if (r2 == 0) goto L_0x0018
            r2 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxBy$1 r2 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxBy$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r0 = r2.label
            int r0 = r0 - r4
            r2.label = r0
            goto L_0x001d
        L_0x0018:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxBy$1 r2 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxBy$1
            r2.<init>(r0)
        L_0x001d:
            java.lang.Object r0 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 0
            r6 = 4
            r7 = 3
            r8 = 2
            r9 = 1
            if (r4 == 0) goto L_0x00ff
            if (r4 == r9) goto L_0x00ce
            if (r4 == r8) goto L_0x00a0
            if (r4 == r7) goto L_0x006c
            if (r4 != r6) goto L_0x0064
            java.lang.Object r1 = r2.L$7
            java.lang.Comparable r1 = (java.lang.Comparable) r1
            java.lang.Object r4 = r2.L$6
            java.lang.Object r5 = r2.L$5
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r10 = r2.L$4
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r2.L$3
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            java.lang.Object r12 = r2.L$2
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r2.L$1
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            java.lang.Object r14 = r2.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009b }
            if (r15 != 0) goto L_0x005f
            r17 = r5
            r5 = r4
            r4 = r10
            r10 = r13
            r13 = r17
            goto L_0x01a5
        L_0x005f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009b }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009b }
            throw r0     // Catch:{ all -> 0x009b }
        L_0x0064:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006c:
            java.lang.Object r1 = r2.L$7
            java.lang.Comparable r1 = (java.lang.Comparable) r1
            java.lang.Object r4 = r2.L$6
            java.lang.Object r5 = r2.L$5
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r10 = r2.L$4
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r2.L$3
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            java.lang.Object r12 = r2.L$2
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r2.L$1
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            java.lang.Object r14 = r2.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009b }
            if (r15 != 0) goto L_0x0096
            r17 = r13
            r13 = r1
            r1 = r12
            r12 = r17
            goto L_0x017b
        L_0x0096:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009b }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009b }
            throw r0     // Catch:{ all -> 0x009b }
        L_0x009b:
            r0 = move-exception
            r2 = r0
            r1 = r12
            goto L_0x01c6
        L_0x00a0:
            java.lang.Object r1 = r2.L$5
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r2.L$4
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r2.L$3
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r10 = r2.L$2
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r2.L$1
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r2.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00c9 }
            if (r13 != 0) goto L_0x00c4
            r17 = r11
            r11 = r1
            r1 = r10
            r10 = r17
            goto L_0x014f
        L_0x00c4:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00c9 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00c9 }
            throw r0     // Catch:{ all -> 0x00c9 }
        L_0x00c9:
            r0 = move-exception
            r2 = r0
            r1 = r10
            goto L_0x01c6
        L_0x00ce:
            java.lang.Object r1 = r2.L$5
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r2.L$4
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r10 = r2.L$3
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            java.lang.Object r11 = r2.L$2
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r2.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r2.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00fa }
            if (r14 != 0) goto L_0x00f5
            r17 = r11
            r11 = r1
            r1 = r17
            r18 = r12
            r12 = r10
            r10 = r18
            goto L_0x0126
        L_0x00f5:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00fa }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00fa }
            throw r0     // Catch:{ all -> 0x00fa }
        L_0x00fa:
            r0 = move-exception
            r2 = r0
            r1 = r11
            goto L_0x01c6
        L_0x00ff:
            boolean r4 = r0 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L_0x01d3
            r0 = r5
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlinx.coroutines.channels.ChannelIterator r4 = r19.iterator()     // Catch:{ all -> 0x01c4 }
            r2.L$0 = r1     // Catch:{ all -> 0x01c4 }
            r10 = r20
            r2.L$1 = r10     // Catch:{ all -> 0x01c4 }
            r2.L$2 = r1     // Catch:{ all -> 0x01c4 }
            r2.L$3 = r0     // Catch:{ all -> 0x01c4 }
            r2.L$4 = r1     // Catch:{ all -> 0x01c4 }
            r2.L$5 = r4     // Catch:{ all -> 0x01c4 }
            r2.label = r9     // Catch:{ all -> 0x01c4 }
            java.lang.Object r11 = r4.hasNext(r2)     // Catch:{ all -> 0x01c4 }
            if (r11 != r3) goto L_0x0121
            return r3
        L_0x0121:
            r12 = r0
            r13 = r1
            r0 = r11
            r11 = r4
            r4 = r13
        L_0x0126:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01c4 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01c4 }
            if (r0 != 0) goto L_0x0138
            kotlin.jvm.internal.InlineMarker.finallyStart(r7)
            r1.cancel(r12)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r7)
            return r5
        L_0x0138:
            r2.L$0 = r13     // Catch:{ all -> 0x01c4 }
            r2.L$1 = r10     // Catch:{ all -> 0x01c4 }
            r2.L$2 = r1     // Catch:{ all -> 0x01c4 }
            r2.L$3 = r12     // Catch:{ all -> 0x01c4 }
            r2.L$4 = r4     // Catch:{ all -> 0x01c4 }
            r2.L$5 = r11     // Catch:{ all -> 0x01c4 }
            r2.label = r8     // Catch:{ all -> 0x01c4 }
            java.lang.Object r0 = r11.next(r2)     // Catch:{ all -> 0x01c4 }
            if (r0 != r3) goto L_0x014d
            return r3
        L_0x014d:
            r5 = r12
            r12 = r13
        L_0x014f:
            java.lang.Object r13 = r10.invoke(r0)     // Catch:{ all -> 0x01c4 }
            java.lang.Comparable r13 = (java.lang.Comparable) r13     // Catch:{ all -> 0x01c4 }
        L_0x0155:
            r2.L$0 = r12     // Catch:{ all -> 0x01c4 }
            r2.L$1 = r10     // Catch:{ all -> 0x01c4 }
            r2.L$2 = r1     // Catch:{ all -> 0x01c4 }
            r2.L$3 = r5     // Catch:{ all -> 0x01c4 }
            r2.L$4 = r4     // Catch:{ all -> 0x01c4 }
            r2.L$5 = r11     // Catch:{ all -> 0x01c4 }
            r2.L$6 = r0     // Catch:{ all -> 0x01c4 }
            r2.L$7 = r13     // Catch:{ all -> 0x01c4 }
            r2.label = r7     // Catch:{ all -> 0x01c4 }
            java.lang.Object r14 = r11.hasNext(r2)     // Catch:{ all -> 0x01c4 }
            if (r14 != r3) goto L_0x016e
            return r3
        L_0x016e:
            r17 = r4
            r4 = r0
            r0 = r14
            r14 = r12
            r12 = r10
            r10 = r17
            r18 = r11
            r11 = r5
            r5 = r18
        L_0x017b:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01c4 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01c4 }
            if (r0 == 0) goto L_0x01ba
            r2.L$0 = r14     // Catch:{ all -> 0x01c4 }
            r2.L$1 = r12     // Catch:{ all -> 0x01c4 }
            r2.L$2 = r1     // Catch:{ all -> 0x01c4 }
            r2.L$3 = r11     // Catch:{ all -> 0x01c4 }
            r2.L$4 = r10     // Catch:{ all -> 0x01c4 }
            r2.L$5 = r5     // Catch:{ all -> 0x01c4 }
            r2.L$6 = r4     // Catch:{ all -> 0x01c4 }
            r2.L$7 = r13     // Catch:{ all -> 0x01c4 }
            r2.label = r6     // Catch:{ all -> 0x01c4 }
            java.lang.Object r0 = r5.next(r2)     // Catch:{ all -> 0x01c4 }
            if (r0 != r3) goto L_0x019c
            return r3
        L_0x019c:
            r17 = r12
            r12 = r1
            r1 = r13
            r13 = r5
            r5 = r4
            r4 = r10
            r10 = r17
        L_0x01a5:
            java.lang.Object r15 = r10.invoke(r0)     // Catch:{ all -> 0x009b }
            java.lang.Comparable r15 = (java.lang.Comparable) r15     // Catch:{ all -> 0x009b }
            int r16 = r1.compareTo(r15)     // Catch:{ all -> 0x009b }
            if (r16 >= 0) goto L_0x01b2
            goto L_0x01b4
        L_0x01b2:
            r15 = r1
            r0 = r5
        L_0x01b4:
            r5 = r11
            r1 = r12
            r11 = r13
            r12 = r14
            r13 = r15
            goto L_0x0155
        L_0x01ba:
            kotlin.jvm.internal.InlineMarker.finallyStart(r8)
            r1.cancel(r11)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r8)
            return r4
        L_0x01c4:
            r0 = move-exception
            r2 = r0
        L_0x01c6:
            throw r2     // Catch:{ all -> 0x01c7 }
        L_0x01c7:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r9)
            r1.cancel(r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r9)
            throw r3
        L_0x01d3:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.maxBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0108 A[SYNTHETIC, Splitter:B:60:0x0108] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0136 A[Catch:{ all -> 0x00ac }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0137 A[Catch:{ all -> 0x00ac }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0147 A[Catch:{ all -> 0x00ac }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object maxWith(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, java.util.Comparator<? super E> r13, kotlin.coroutines.Continuation<? super E> r14) {
        /*
            boolean r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxWith$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxWith$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxWith$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxWith$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$maxWith$1
            r0.<init>(r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            if (r2 == 0) goto L_0x00d5
            if (r2 == r7) goto L_0x00af
            if (r2 == r6) goto L_0x0089
            if (r2 == r5) goto L_0x005d
            if (r2 != r4) goto L_0x0055
            java.lang.Object r12 = r0.L$6
            java.lang.Object r13 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r3 = r0.L$3
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            java.util.Comparator r7 = (java.util.Comparator) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r14 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0085 }
            if (r9 != 0) goto L_0x0050
            goto L_0x0161
        L_0x0050:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14     // Catch:{ all -> 0x0085 }
            java.lang.Throwable r12 = r14.exception     // Catch:{ all -> 0x0085 }
            throw r12     // Catch:{ all -> 0x0085 }
        L_0x0055:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x005d:
            java.lang.Object r12 = r0.L$6
            java.lang.Object r13 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r3 = r0.L$3
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            java.util.Comparator r7 = (java.util.Comparator) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r14 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0085 }
            if (r9 != 0) goto L_0x0080
            r11 = r6
            r6 = r3
            r3 = r11
            goto L_0x013f
        L_0x0080:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14     // Catch:{ all -> 0x0085 }
            java.lang.Throwable r12 = r14.exception     // Catch:{ all -> 0x0085 }
            throw r12     // Catch:{ all -> 0x0085 }
        L_0x0085:
            r12 = move-exception
            r3 = r6
            goto L_0x0177
        L_0x0089:
            java.lang.Object r12 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r13 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            java.lang.Object r2 = r0.L$3
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r3 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3
            java.lang.Object r6 = r0.L$1
            java.util.Comparator r6 = (java.util.Comparator) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            boolean r8 = r14 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00ac }
            if (r8 != 0) goto L_0x00a7
            goto L_0x0120
        L_0x00a7:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14     // Catch:{ all -> 0x00ac }
            java.lang.Throwable r12 = r14.exception     // Catch:{ all -> 0x00ac }
            throw r12     // Catch:{ all -> 0x00ac }
        L_0x00ac:
            r12 = move-exception
            goto L_0x0177
        L_0x00af:
            java.lang.Object r12 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r13 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            java.lang.Object r2 = r0.L$3
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$1
            java.util.Comparator r8 = (java.util.Comparator) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            boolean r10 = r14 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00d1 }
            if (r10 != 0) goto L_0x00cc
            goto L_0x00fc
        L_0x00cc:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14     // Catch:{ all -> 0x00d1 }
            java.lang.Throwable r12 = r14.exception     // Catch:{ all -> 0x00d1 }
            throw r12     // Catch:{ all -> 0x00d1 }
        L_0x00d1:
            r12 = move-exception
            r3 = r7
            goto L_0x0177
        L_0x00d5:
            boolean r2 = r14 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x017d
            r14 = r3
            java.lang.Throwable r14 = (java.lang.Throwable) r14
            kotlinx.coroutines.channels.ChannelIterator r2 = r12.iterator()     // Catch:{ all -> 0x0174 }
            r0.L$0 = r12     // Catch:{ all -> 0x0174 }
            r0.L$1 = r13     // Catch:{ all -> 0x0174 }
            r0.L$2 = r12     // Catch:{ all -> 0x0174 }
            r0.L$3 = r14     // Catch:{ all -> 0x0174 }
            r0.L$4 = r12     // Catch:{ all -> 0x0174 }
            r0.L$5 = r2     // Catch:{ all -> 0x0174 }
            r0.label = r7     // Catch:{ all -> 0x0174 }
            java.lang.Object r7 = r2.hasNext(r0)     // Catch:{ all -> 0x0174 }
            if (r7 != r1) goto L_0x00f5
            return r1
        L_0x00f5:
            r9 = r12
            r8 = r13
            r13 = r9
            r12 = r2
            r2 = r14
            r14 = r7
            r7 = r13
        L_0x00fc:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ all -> 0x00d1 }
            boolean r14 = r14.booleanValue()     // Catch:{ all -> 0x00d1 }
            if (r14 != 0) goto L_0x0108
            r7.cancel(r2)
            return r3
        L_0x0108:
            r0.L$0 = r9     // Catch:{ all -> 0x00d1 }
            r0.L$1 = r8     // Catch:{ all -> 0x00d1 }
            r0.L$2 = r7     // Catch:{ all -> 0x00d1 }
            r0.L$3 = r2     // Catch:{ all -> 0x00d1 }
            r0.L$4 = r13     // Catch:{ all -> 0x00d1 }
            r0.L$5 = r12     // Catch:{ all -> 0x00d1 }
            r0.label = r6     // Catch:{ all -> 0x00d1 }
            java.lang.Object r14 = r12.next(r0)     // Catch:{ all -> 0x00d1 }
            if (r14 != r1) goto L_0x011d
            return r1
        L_0x011d:
            r3 = r7
            r6 = r8
            r7 = r9
        L_0x0120:
            r0.L$0 = r7     // Catch:{ all -> 0x00ac }
            r0.L$1 = r6     // Catch:{ all -> 0x00ac }
            r0.L$2 = r3     // Catch:{ all -> 0x00ac }
            r0.L$3 = r2     // Catch:{ all -> 0x00ac }
            r0.L$4 = r13     // Catch:{ all -> 0x00ac }
            r0.L$5 = r12     // Catch:{ all -> 0x00ac }
            r0.L$6 = r14     // Catch:{ all -> 0x00ac }
            r0.label = r5     // Catch:{ all -> 0x00ac }
            java.lang.Object r8 = r12.hasNext(r0)     // Catch:{ all -> 0x00ac }
            if (r8 != r1) goto L_0x0137
            return r1
        L_0x0137:
            r11 = r13
            r13 = r12
            r12 = r14
            r14 = r8
            r8 = r7
            r7 = r6
            r6 = r2
            r2 = r11
        L_0x013f:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ all -> 0x00ac }
            boolean r14 = r14.booleanValue()     // Catch:{ all -> 0x00ac }
            if (r14 == 0) goto L_0x0170
            r0.L$0 = r8     // Catch:{ all -> 0x00ac }
            r0.L$1 = r7     // Catch:{ all -> 0x00ac }
            r0.L$2 = r3     // Catch:{ all -> 0x00ac }
            r0.L$3 = r6     // Catch:{ all -> 0x00ac }
            r0.L$4 = r2     // Catch:{ all -> 0x00ac }
            r0.L$5 = r13     // Catch:{ all -> 0x00ac }
            r0.L$6 = r12     // Catch:{ all -> 0x00ac }
            r0.label = r4     // Catch:{ all -> 0x00ac }
            java.lang.Object r14 = r13.next(r0)     // Catch:{ all -> 0x00ac }
            if (r14 != r1) goto L_0x015e
            return r1
        L_0x015e:
            r11 = r6
            r6 = r3
            r3 = r11
        L_0x0161:
            int r9 = r7.compare(r12, r14)     // Catch:{ all -> 0x0085 }
            if (r9 >= 0) goto L_0x0168
            goto L_0x0169
        L_0x0168:
            r14 = r12
        L_0x0169:
            r12 = r13
            r13 = r2
            r2 = r3
            r3 = r6
            r6 = r7
            r7 = r8
            goto L_0x0120
        L_0x0170:
            r3.cancel(r6)
            return r12
        L_0x0174:
            r13 = move-exception
            r3 = r12
            r12 = r13
        L_0x0177:
            throw r12     // Catch:{ all -> 0x0178 }
        L_0x0178:
            r13 = move-exception
            r3.cancel(r12)
            throw r13
        L_0x017d:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14
            java.lang.Throwable r12 = r14.exception
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.maxWith(kotlinx.coroutines.channels.ReceiveChannel, java.util.Comparator, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x012e  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0138 A[SYNTHETIC, Splitter:B:60:0x0138] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x016d A[Catch:{ all -> 0x01c4 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x016e A[Catch:{ all -> 0x01c4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0183 A[Catch:{ all -> 0x01c4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01b2  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E, R extends java.lang.Comparable<? super R>> java.lang.Object minBy(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r19, kotlin.jvm.functions.Function1<? super E, ? extends R> r20, kotlin.coroutines.Continuation<? super E> r21) {
        /*
            r1 = r19
            r0 = r21
            boolean r2 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minBy$1
            if (r2 == 0) goto L_0x0018
            r2 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minBy$1 r2 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minBy$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r0 = r2.label
            int r0 = r0 - r4
            r2.label = r0
            goto L_0x001d
        L_0x0018:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minBy$1 r2 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minBy$1
            r2.<init>(r0)
        L_0x001d:
            java.lang.Object r0 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 0
            r6 = 4
            r7 = 3
            r8 = 2
            r9 = 1
            if (r4 == 0) goto L_0x00ff
            if (r4 == r9) goto L_0x00ce
            if (r4 == r8) goto L_0x00a0
            if (r4 == r7) goto L_0x006c
            if (r4 != r6) goto L_0x0064
            java.lang.Object r1 = r2.L$7
            java.lang.Comparable r1 = (java.lang.Comparable) r1
            java.lang.Object r4 = r2.L$6
            java.lang.Object r5 = r2.L$5
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r10 = r2.L$4
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r2.L$3
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            java.lang.Object r12 = r2.L$2
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r2.L$1
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            java.lang.Object r14 = r2.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009b }
            if (r15 != 0) goto L_0x005f
            r17 = r5
            r5 = r4
            r4 = r10
            r10 = r13
            r13 = r17
            goto L_0x01a5
        L_0x005f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009b }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009b }
            throw r0     // Catch:{ all -> 0x009b }
        L_0x0064:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006c:
            java.lang.Object r1 = r2.L$7
            java.lang.Comparable r1 = (java.lang.Comparable) r1
            java.lang.Object r4 = r2.L$6
            java.lang.Object r5 = r2.L$5
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r10 = r2.L$4
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r2.L$3
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            java.lang.Object r12 = r2.L$2
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r2.L$1
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            java.lang.Object r14 = r2.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009b }
            if (r15 != 0) goto L_0x0096
            r17 = r13
            r13 = r1
            r1 = r12
            r12 = r17
            goto L_0x017b
        L_0x0096:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009b }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009b }
            throw r0     // Catch:{ all -> 0x009b }
        L_0x009b:
            r0 = move-exception
            r2 = r0
            r1 = r12
            goto L_0x01c6
        L_0x00a0:
            java.lang.Object r1 = r2.L$5
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r2.L$4
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r2.L$3
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r10 = r2.L$2
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r2.L$1
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r2.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00c9 }
            if (r13 != 0) goto L_0x00c4
            r17 = r11
            r11 = r1
            r1 = r10
            r10 = r17
            goto L_0x014f
        L_0x00c4:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00c9 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00c9 }
            throw r0     // Catch:{ all -> 0x00c9 }
        L_0x00c9:
            r0 = move-exception
            r2 = r0
            r1 = r10
            goto L_0x01c6
        L_0x00ce:
            java.lang.Object r1 = r2.L$5
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r2.L$4
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r10 = r2.L$3
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            java.lang.Object r11 = r2.L$2
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r2.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r2.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00fa }
            if (r14 != 0) goto L_0x00f5
            r17 = r11
            r11 = r1
            r1 = r17
            r18 = r12
            r12 = r10
            r10 = r18
            goto L_0x0126
        L_0x00f5:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00fa }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00fa }
            throw r0     // Catch:{ all -> 0x00fa }
        L_0x00fa:
            r0 = move-exception
            r2 = r0
            r1 = r11
            goto L_0x01c6
        L_0x00ff:
            boolean r4 = r0 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L_0x01d3
            r0 = r5
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlinx.coroutines.channels.ChannelIterator r4 = r19.iterator()     // Catch:{ all -> 0x01c4 }
            r2.L$0 = r1     // Catch:{ all -> 0x01c4 }
            r10 = r20
            r2.L$1 = r10     // Catch:{ all -> 0x01c4 }
            r2.L$2 = r1     // Catch:{ all -> 0x01c4 }
            r2.L$3 = r0     // Catch:{ all -> 0x01c4 }
            r2.L$4 = r1     // Catch:{ all -> 0x01c4 }
            r2.L$5 = r4     // Catch:{ all -> 0x01c4 }
            r2.label = r9     // Catch:{ all -> 0x01c4 }
            java.lang.Object r11 = r4.hasNext(r2)     // Catch:{ all -> 0x01c4 }
            if (r11 != r3) goto L_0x0121
            return r3
        L_0x0121:
            r12 = r0
            r13 = r1
            r0 = r11
            r11 = r4
            r4 = r13
        L_0x0126:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01c4 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01c4 }
            if (r0 != 0) goto L_0x0138
            kotlin.jvm.internal.InlineMarker.finallyStart(r7)
            r1.cancel(r12)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r7)
            return r5
        L_0x0138:
            r2.L$0 = r13     // Catch:{ all -> 0x01c4 }
            r2.L$1 = r10     // Catch:{ all -> 0x01c4 }
            r2.L$2 = r1     // Catch:{ all -> 0x01c4 }
            r2.L$3 = r12     // Catch:{ all -> 0x01c4 }
            r2.L$4 = r4     // Catch:{ all -> 0x01c4 }
            r2.L$5 = r11     // Catch:{ all -> 0x01c4 }
            r2.label = r8     // Catch:{ all -> 0x01c4 }
            java.lang.Object r0 = r11.next(r2)     // Catch:{ all -> 0x01c4 }
            if (r0 != r3) goto L_0x014d
            return r3
        L_0x014d:
            r5 = r12
            r12 = r13
        L_0x014f:
            java.lang.Object r13 = r10.invoke(r0)     // Catch:{ all -> 0x01c4 }
            java.lang.Comparable r13 = (java.lang.Comparable) r13     // Catch:{ all -> 0x01c4 }
        L_0x0155:
            r2.L$0 = r12     // Catch:{ all -> 0x01c4 }
            r2.L$1 = r10     // Catch:{ all -> 0x01c4 }
            r2.L$2 = r1     // Catch:{ all -> 0x01c4 }
            r2.L$3 = r5     // Catch:{ all -> 0x01c4 }
            r2.L$4 = r4     // Catch:{ all -> 0x01c4 }
            r2.L$5 = r11     // Catch:{ all -> 0x01c4 }
            r2.L$6 = r0     // Catch:{ all -> 0x01c4 }
            r2.L$7 = r13     // Catch:{ all -> 0x01c4 }
            r2.label = r7     // Catch:{ all -> 0x01c4 }
            java.lang.Object r14 = r11.hasNext(r2)     // Catch:{ all -> 0x01c4 }
            if (r14 != r3) goto L_0x016e
            return r3
        L_0x016e:
            r17 = r4
            r4 = r0
            r0 = r14
            r14 = r12
            r12 = r10
            r10 = r17
            r18 = r11
            r11 = r5
            r5 = r18
        L_0x017b:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01c4 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01c4 }
            if (r0 == 0) goto L_0x01ba
            r2.L$0 = r14     // Catch:{ all -> 0x01c4 }
            r2.L$1 = r12     // Catch:{ all -> 0x01c4 }
            r2.L$2 = r1     // Catch:{ all -> 0x01c4 }
            r2.L$3 = r11     // Catch:{ all -> 0x01c4 }
            r2.L$4 = r10     // Catch:{ all -> 0x01c4 }
            r2.L$5 = r5     // Catch:{ all -> 0x01c4 }
            r2.L$6 = r4     // Catch:{ all -> 0x01c4 }
            r2.L$7 = r13     // Catch:{ all -> 0x01c4 }
            r2.label = r6     // Catch:{ all -> 0x01c4 }
            java.lang.Object r0 = r5.next(r2)     // Catch:{ all -> 0x01c4 }
            if (r0 != r3) goto L_0x019c
            return r3
        L_0x019c:
            r17 = r12
            r12 = r1
            r1 = r13
            r13 = r5
            r5 = r4
            r4 = r10
            r10 = r17
        L_0x01a5:
            java.lang.Object r15 = r10.invoke(r0)     // Catch:{ all -> 0x009b }
            java.lang.Comparable r15 = (java.lang.Comparable) r15     // Catch:{ all -> 0x009b }
            int r16 = r1.compareTo(r15)     // Catch:{ all -> 0x009b }
            if (r16 <= 0) goto L_0x01b2
            goto L_0x01b4
        L_0x01b2:
            r15 = r1
            r0 = r5
        L_0x01b4:
            r5 = r11
            r1 = r12
            r11 = r13
            r12 = r14
            r13 = r15
            goto L_0x0155
        L_0x01ba:
            kotlin.jvm.internal.InlineMarker.finallyStart(r8)
            r1.cancel(r11)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r8)
            return r4
        L_0x01c4:
            r0 = move-exception
            r2 = r0
        L_0x01c6:
            throw r2     // Catch:{ all -> 0x01c7 }
        L_0x01c7:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r9)
            r1.cancel(r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r9)
            throw r3
        L_0x01d3:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.minBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0108 A[SYNTHETIC, Splitter:B:60:0x0108] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0136 A[Catch:{ all -> 0x00ac }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0137 A[Catch:{ all -> 0x00ac }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0147 A[Catch:{ all -> 0x00ac }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object minWith(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r12, java.util.Comparator<? super E> r13, kotlin.coroutines.Continuation<? super E> r14) {
        /*
            boolean r0 = r14 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minWith$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minWith$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minWith$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minWith$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$minWith$1
            r0.<init>(r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            if (r2 == 0) goto L_0x00d5
            if (r2 == r7) goto L_0x00af
            if (r2 == r6) goto L_0x0089
            if (r2 == r5) goto L_0x005d
            if (r2 != r4) goto L_0x0055
            java.lang.Object r12 = r0.L$6
            java.lang.Object r13 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r3 = r0.L$3
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            java.util.Comparator r7 = (java.util.Comparator) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r14 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0085 }
            if (r9 != 0) goto L_0x0050
            goto L_0x0161
        L_0x0050:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14     // Catch:{ all -> 0x0085 }
            java.lang.Throwable r12 = r14.exception     // Catch:{ all -> 0x0085 }
            throw r12     // Catch:{ all -> 0x0085 }
        L_0x0055:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x005d:
            java.lang.Object r12 = r0.L$6
            java.lang.Object r13 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r3 = r0.L$3
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            java.util.Comparator r7 = (java.util.Comparator) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r14 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0085 }
            if (r9 != 0) goto L_0x0080
            r11 = r6
            r6 = r3
            r3 = r11
            goto L_0x013f
        L_0x0080:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14     // Catch:{ all -> 0x0085 }
            java.lang.Throwable r12 = r14.exception     // Catch:{ all -> 0x0085 }
            throw r12     // Catch:{ all -> 0x0085 }
        L_0x0085:
            r12 = move-exception
            r3 = r6
            goto L_0x0177
        L_0x0089:
            java.lang.Object r12 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r13 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            java.lang.Object r2 = r0.L$3
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r3 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3
            java.lang.Object r6 = r0.L$1
            java.util.Comparator r6 = (java.util.Comparator) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            boolean r8 = r14 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00ac }
            if (r8 != 0) goto L_0x00a7
            goto L_0x0120
        L_0x00a7:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14     // Catch:{ all -> 0x00ac }
            java.lang.Throwable r12 = r14.exception     // Catch:{ all -> 0x00ac }
            throw r12     // Catch:{ all -> 0x00ac }
        L_0x00ac:
            r12 = move-exception
            goto L_0x0177
        L_0x00af:
            java.lang.Object r12 = r0.L$5
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r13 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            java.lang.Object r2 = r0.L$3
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$1
            java.util.Comparator r8 = (java.util.Comparator) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            boolean r10 = r14 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00d1 }
            if (r10 != 0) goto L_0x00cc
            goto L_0x00fc
        L_0x00cc:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14     // Catch:{ all -> 0x00d1 }
            java.lang.Throwable r12 = r14.exception     // Catch:{ all -> 0x00d1 }
            throw r12     // Catch:{ all -> 0x00d1 }
        L_0x00d1:
            r12 = move-exception
            r3 = r7
            goto L_0x0177
        L_0x00d5:
            boolean r2 = r14 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x017d
            r14 = r3
            java.lang.Throwable r14 = (java.lang.Throwable) r14
            kotlinx.coroutines.channels.ChannelIterator r2 = r12.iterator()     // Catch:{ all -> 0x0174 }
            r0.L$0 = r12     // Catch:{ all -> 0x0174 }
            r0.L$1 = r13     // Catch:{ all -> 0x0174 }
            r0.L$2 = r12     // Catch:{ all -> 0x0174 }
            r0.L$3 = r14     // Catch:{ all -> 0x0174 }
            r0.L$4 = r12     // Catch:{ all -> 0x0174 }
            r0.L$5 = r2     // Catch:{ all -> 0x0174 }
            r0.label = r7     // Catch:{ all -> 0x0174 }
            java.lang.Object r7 = r2.hasNext(r0)     // Catch:{ all -> 0x0174 }
            if (r7 != r1) goto L_0x00f5
            return r1
        L_0x00f5:
            r9 = r12
            r8 = r13
            r13 = r9
            r12 = r2
            r2 = r14
            r14 = r7
            r7 = r13
        L_0x00fc:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ all -> 0x00d1 }
            boolean r14 = r14.booleanValue()     // Catch:{ all -> 0x00d1 }
            if (r14 != 0) goto L_0x0108
            r7.cancel(r2)
            return r3
        L_0x0108:
            r0.L$0 = r9     // Catch:{ all -> 0x00d1 }
            r0.L$1 = r8     // Catch:{ all -> 0x00d1 }
            r0.L$2 = r7     // Catch:{ all -> 0x00d1 }
            r0.L$3 = r2     // Catch:{ all -> 0x00d1 }
            r0.L$4 = r13     // Catch:{ all -> 0x00d1 }
            r0.L$5 = r12     // Catch:{ all -> 0x00d1 }
            r0.label = r6     // Catch:{ all -> 0x00d1 }
            java.lang.Object r14 = r12.next(r0)     // Catch:{ all -> 0x00d1 }
            if (r14 != r1) goto L_0x011d
            return r1
        L_0x011d:
            r3 = r7
            r6 = r8
            r7 = r9
        L_0x0120:
            r0.L$0 = r7     // Catch:{ all -> 0x00ac }
            r0.L$1 = r6     // Catch:{ all -> 0x00ac }
            r0.L$2 = r3     // Catch:{ all -> 0x00ac }
            r0.L$3 = r2     // Catch:{ all -> 0x00ac }
            r0.L$4 = r13     // Catch:{ all -> 0x00ac }
            r0.L$5 = r12     // Catch:{ all -> 0x00ac }
            r0.L$6 = r14     // Catch:{ all -> 0x00ac }
            r0.label = r5     // Catch:{ all -> 0x00ac }
            java.lang.Object r8 = r12.hasNext(r0)     // Catch:{ all -> 0x00ac }
            if (r8 != r1) goto L_0x0137
            return r1
        L_0x0137:
            r11 = r13
            r13 = r12
            r12 = r14
            r14 = r8
            r8 = r7
            r7 = r6
            r6 = r2
            r2 = r11
        L_0x013f:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ all -> 0x00ac }
            boolean r14 = r14.booleanValue()     // Catch:{ all -> 0x00ac }
            if (r14 == 0) goto L_0x0170
            r0.L$0 = r8     // Catch:{ all -> 0x00ac }
            r0.L$1 = r7     // Catch:{ all -> 0x00ac }
            r0.L$2 = r3     // Catch:{ all -> 0x00ac }
            r0.L$3 = r6     // Catch:{ all -> 0x00ac }
            r0.L$4 = r2     // Catch:{ all -> 0x00ac }
            r0.L$5 = r13     // Catch:{ all -> 0x00ac }
            r0.L$6 = r12     // Catch:{ all -> 0x00ac }
            r0.label = r4     // Catch:{ all -> 0x00ac }
            java.lang.Object r14 = r13.next(r0)     // Catch:{ all -> 0x00ac }
            if (r14 != r1) goto L_0x015e
            return r1
        L_0x015e:
            r11 = r6
            r6 = r3
            r3 = r11
        L_0x0161:
            int r9 = r7.compare(r12, r14)     // Catch:{ all -> 0x0085 }
            if (r9 <= 0) goto L_0x0168
            goto L_0x0169
        L_0x0168:
            r14 = r12
        L_0x0169:
            r12 = r13
            r13 = r2
            r2 = r3
            r3 = r6
            r6 = r7
            r7 = r8
            goto L_0x0120
        L_0x0170:
            r3.cancel(r6)
            return r12
        L_0x0174:
            r13 = move-exception
            r3 = r12
            r12 = r13
        L_0x0177:
            throw r12     // Catch:{ all -> 0x0178 }
        L_0x0178:
            r13 = move-exception
            r3.cancel(r12)
            throw r13
        L_0x017d:
            kotlin.Result$Failure r14 = (kotlin.Result.Failure) r14
            java.lang.Throwable r12 = r14.exception
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.minWith(kotlinx.coroutines.channels.ReceiveChannel, java.util.Comparator, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0071 A[Catch:{ all -> 0x007b }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0072 A[Catch:{ all -> 0x007b }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object none(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r4, kotlin.coroutines.Continuation<? super java.lang.Boolean> r5) {
        /*
            boolean r0 = r5 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$1
            r0.<init>(r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x004d
            if (r2 != r3) goto L_0x0045
            java.lang.Object r4 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r4 = r0.L$2
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            java.lang.Object r1 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r1 = (kotlinx.coroutines.channels.ReceiveChannel) r1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r0 = (kotlinx.coroutines.channels.ReceiveChannel) r0
            boolean r0 = r5 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0043 }
            if (r0 != 0) goto L_0x003e
            r0 = r5
            r5 = r4
            r4 = r1
            goto L_0x0069
        L_0x003e:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5     // Catch:{ all -> 0x0043 }
            java.lang.Throwable r4 = r5.exception     // Catch:{ all -> 0x0043 }
            throw r4     // Catch:{ all -> 0x0043 }
        L_0x0043:
            r4 = move-exception
            goto L_0x007e
        L_0x0045:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x004d:
            boolean r2 = r5 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x0084
            r5 = 0
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            kotlinx.coroutines.channels.ChannelIterator r2 = r4.iterator()     // Catch:{ all -> 0x007b }
            r0.L$0 = r4     // Catch:{ all -> 0x007b }
            r0.L$1 = r4     // Catch:{ all -> 0x007b }
            r0.L$2 = r5     // Catch:{ all -> 0x007b }
            r0.L$3 = r4     // Catch:{ all -> 0x007b }
            r0.label = r3     // Catch:{ all -> 0x007b }
            java.lang.Object r0 = r2.hasNext(r0)     // Catch:{ all -> 0x007b }
            if (r0 != r1) goto L_0x0069
            return r1
        L_0x0069:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x007b }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x007b }
            if (r0 != 0) goto L_0x0072
            goto L_0x0073
        L_0x0072:
            r3 = 0
        L_0x0073:
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)     // Catch:{ all -> 0x007b }
            r4.cancel(r5)
            return r0
        L_0x007b:
            r5 = move-exception
            r1 = r4
            r4 = r5
        L_0x007e:
            throw r4     // Catch:{ all -> 0x007f }
        L_0x007f:
            r5 = move-exception
            r1.cancel(r4)
            throw r5
        L_0x0084:
            kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
            java.lang.Throwable r4 = r5.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.none(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ba A[Catch:{ all -> 0x0118 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ca A[Catch:{ all -> 0x0118 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object none(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r13, kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r14, kotlin.coroutines.Continuation<? super java.lang.Boolean> r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$3
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$none$3
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0090
            if (r2 == r4) goto L_0x0062
            if (r2 != r3) goto L_0x005a
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008c }
            if (r9 != 0) goto L_0x0055
            r10 = r8
            r8 = r13
            r13 = r10
        L_0x004c:
            r11 = r0
            r0 = r14
            r14 = r6
            r6 = r11
            r12 = r7
            r7 = r1
            r1 = r12
            goto L_0x00e8
        L_0x0055:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x008c }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x008c }
            throw r13     // Catch:{ all -> 0x008c }
        L_0x005a:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0062:
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r2 = r0.L$4
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            boolean r9 = r15 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x008c }
            if (r9 != 0) goto L_0x0087
            r10 = r8
            r8 = r15
            r15 = r5
            r5 = r10
            goto L_0x00c2
        L_0x0087:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15     // Catch:{ all -> 0x008c }
            java.lang.Throwable r13 = r15.exception     // Catch:{ all -> 0x008c }
            throw r13     // Catch:{ all -> 0x008c }
        L_0x008c:
            r13 = move-exception
            r15 = r5
            goto L_0x011d
        L_0x0090:
            boolean r2 = r15 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L_0x0129
            r15 = 0
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            kotlinx.coroutines.channels.ChannelIterator r2 = r13.iterator()     // Catch:{ all -> 0x011a }
            r5 = r0
            r6 = r1
            r7 = r2
            r0 = r13
            r1 = r14
            r2 = r15
            r14 = r0
            r15 = r14
        L_0x00a3:
            r5.L$0 = r13     // Catch:{ all -> 0x0118 }
            r5.L$1 = r1     // Catch:{ all -> 0x0118 }
            r5.L$2 = r14     // Catch:{ all -> 0x0118 }
            r5.L$3 = r15     // Catch:{ all -> 0x0118 }
            r5.L$4 = r2     // Catch:{ all -> 0x0118 }
            r5.L$5 = r0     // Catch:{ all -> 0x0118 }
            r5.L$6 = r7     // Catch:{ all -> 0x0118 }
            r5.label = r4     // Catch:{ all -> 0x0118 }
            java.lang.Object r8 = r7.hasNext(r5)     // Catch:{ all -> 0x0118 }
            if (r8 != r6) goto L_0x00ba
            return r6
        L_0x00ba:
            r10 = r5
            r5 = r13
            r13 = r7
            r7 = r1
            r1 = r6
            r6 = r14
            r14 = r0
            r0 = r10
        L_0x00c2:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x0118 }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x0118 }
            if (r8 == 0) goto L_0x0108
            r0.L$0 = r5     // Catch:{ all -> 0x0118 }
            r0.L$1 = r7     // Catch:{ all -> 0x0118 }
            r0.L$2 = r6     // Catch:{ all -> 0x0118 }
            r0.L$3 = r15     // Catch:{ all -> 0x0118 }
            r0.L$4 = r2     // Catch:{ all -> 0x0118 }
            r0.L$5 = r14     // Catch:{ all -> 0x0118 }
            r0.L$6 = r13     // Catch:{ all -> 0x0118 }
            r0.label = r3     // Catch:{ all -> 0x0118 }
            java.lang.Object r8 = r13.next(r0)     // Catch:{ all -> 0x0118 }
            if (r8 != r1) goto L_0x00e1
            return r1
        L_0x00e1:
            r10 = r8
            r8 = r13
            r13 = r5
            r5 = r15
            r15 = r10
            goto L_0x004c
        L_0x00e8:
            java.lang.Object r15 = r1.invoke(r15)     // Catch:{ all -> 0x008c }
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ all -> 0x008c }
            boolean r15 = r15.booleanValue()     // Catch:{ all -> 0x008c }
            if (r15 == 0) goto L_0x0103
            r13 = 0
            java.lang.Boolean r13 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r13)     // Catch:{ all -> 0x008c }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            r5.cancel(r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r13
        L_0x0103:
            r15 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            goto L_0x00a3
        L_0x0108:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0118 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            r15.cancel(r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            java.lang.Boolean r13 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r13
        L_0x0118:
            r13 = move-exception
            goto L_0x011d
        L_0x011a:
            r14 = move-exception
            r15 = r13
            r13 = r14
        L_0x011d:
            throw r13     // Catch:{ all -> 0x011e }
        L_0x011e:
            r14 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r4)
            r15.cancel(r13)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
            throw r14
        L_0x0129:
            kotlin.Result$Failure r15 = (kotlin.Result.Failure) r15
            java.lang.Throwable r13 = r15.exception
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.none(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x011e A[Catch:{ all -> 0x00eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0150 A[Catch:{ all -> 0x00eb }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0151 A[Catch:{ all -> 0x00eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0160 A[Catch:{ all -> 0x00eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0197 A[SYNTHETIC, Splitter:B:78:0x0197] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <S, E extends S> java.lang.Object reduce(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r17, kotlin.jvm.functions.Function2<? super S, ? super E, ? extends S> r18, kotlin.coroutines.Continuation<? super S> r19) {
        /*
            r1 = r17
            r0 = r19
            boolean r2 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduce$1
            if (r2 == 0) goto L_0x0018
            r2 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduce$1 r2 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduce$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r0 = r2.label
            int r0 = r0 - r4
            r2.label = r0
            goto L_0x001d
        L_0x0018:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduce$1 r2 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduce$1
            r2.<init>(r0)
        L_0x001d:
            java.lang.Object r0 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 4
            r6 = 3
            r7 = 2
            r8 = 1
            if (r4 == 0) goto L_0x00ef
            if (r4 == r8) goto L_0x00c2
            if (r4 == r7) goto L_0x009f
            if (r4 == r6) goto L_0x006b
            if (r4 != r5) goto L_0x0063
            java.lang.Object r1 = r2.L$8
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            java.lang.Object r4 = r2.L$7
            java.lang.Object r9 = r2.L$6
            java.lang.Object r9 = r2.L$5
            kotlinx.coroutines.channels.ChannelIterator r9 = (kotlinx.coroutines.channels.ChannelIterator) r9
            java.lang.Object r10 = r2.L$4
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r2.L$3
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            java.lang.Object r12 = r2.L$2
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r2.L$1
            kotlin.jvm.functions.Function2 r13 = (kotlin.jvm.functions.Function2) r13
            java.lang.Object r14 = r2.L$0
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            boolean r15 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x005e }
            if (r15 != 0) goto L_0x0059
            goto L_0x0182
        L_0x0059:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x005e }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x005e }
            throw r0     // Catch:{ all -> 0x005e }
        L_0x005e:
            r0 = move-exception
            r1 = r0
            r10 = r12
            goto L_0x01a5
        L_0x0063:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006b:
            java.lang.Object r1 = r2.L$6
            java.lang.Object r4 = r2.L$5
            kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
            java.lang.Object r9 = r2.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r2.L$3
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            java.lang.Object r11 = r2.L$2
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r2.L$1
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            java.lang.Object r13 = r2.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009a }
            if (r14 != 0) goto L_0x0095
            r14 = r13
            r16 = r4
            r4 = r1
            r1 = r12
            r12 = r10
            r10 = r11
            r11 = r9
            r9 = r16
            goto L_0x0158
        L_0x0095:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009a }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009a }
            throw r0     // Catch:{ all -> 0x009a }
        L_0x009a:
            r0 = move-exception
            r1 = r0
            r10 = r11
            goto L_0x01a5
        L_0x009f:
            java.lang.Object r1 = r2.L$5
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r2.L$4
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r9 = r2.L$3
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            java.lang.Object r10 = r2.L$2
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r2.L$1
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            java.lang.Object r12 = r2.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00eb }
            if (r13 != 0) goto L_0x00bd
            goto L_0x013a
        L_0x00bd:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00eb }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00eb }
            throw r0     // Catch:{ all -> 0x00eb }
        L_0x00c2:
            java.lang.Object r1 = r2.L$5
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r2.L$4
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r9 = r2.L$3
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            java.lang.Object r10 = r2.L$2
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r2.L$1
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            java.lang.Object r12 = r2.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00eb }
            if (r13 != 0) goto L_0x00e6
            r16 = r11
            r11 = r1
            r1 = r12
            r12 = r9
            r9 = r16
            goto L_0x0116
        L_0x00e6:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00eb }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00eb }
            throw r0     // Catch:{ all -> 0x00eb }
        L_0x00eb:
            r0 = move-exception
        L_0x00ec:
            r1 = r0
            goto L_0x01a5
        L_0x00ef:
            boolean r4 = r0 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L_0x01b2
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlinx.coroutines.channels.ChannelIterator r4 = r17.iterator()     // Catch:{ all -> 0x01a1 }
            r2.L$0 = r1     // Catch:{ all -> 0x01a1 }
            r9 = r18
            r2.L$1 = r9     // Catch:{ all -> 0x01a1 }
            r2.L$2 = r1     // Catch:{ all -> 0x01a1 }
            r2.L$3 = r0     // Catch:{ all -> 0x01a1 }
            r2.L$4 = r1     // Catch:{ all -> 0x01a1 }
            r2.L$5 = r4     // Catch:{ all -> 0x01a1 }
            r2.label = r8     // Catch:{ all -> 0x01a1 }
            java.lang.Object r10 = r4.hasNext(r2)     // Catch:{ all -> 0x01a1 }
            if (r10 != r3) goto L_0x0111
            return r3
        L_0x0111:
            r12 = r0
            r11 = r4
            r0 = r10
            r4 = r1
            r10 = r4
        L_0x0116:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x00eb }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x00eb }
            if (r0 == 0) goto L_0x0197
            r2.L$0 = r1     // Catch:{ all -> 0x00eb }
            r2.L$1 = r9     // Catch:{ all -> 0x00eb }
            r2.L$2 = r10     // Catch:{ all -> 0x00eb }
            r2.L$3 = r12     // Catch:{ all -> 0x00eb }
            r2.L$4 = r4     // Catch:{ all -> 0x00eb }
            r2.L$5 = r11     // Catch:{ all -> 0x00eb }
            r2.label = r7     // Catch:{ all -> 0x00eb }
            java.lang.Object r0 = r11.next(r2)     // Catch:{ all -> 0x00eb }
            if (r0 != r3) goto L_0x0133
            return r3
        L_0x0133:
            r16 = r12
            r12 = r1
            r1 = r11
            r11 = r9
            r9 = r16
        L_0x013a:
            r2.L$0 = r12     // Catch:{ all -> 0x00eb }
            r2.L$1 = r11     // Catch:{ all -> 0x00eb }
            r2.L$2 = r10     // Catch:{ all -> 0x00eb }
            r2.L$3 = r9     // Catch:{ all -> 0x00eb }
            r2.L$4 = r4     // Catch:{ all -> 0x00eb }
            r2.L$5 = r1     // Catch:{ all -> 0x00eb }
            r2.L$6 = r0     // Catch:{ all -> 0x00eb }
            r2.label = r6     // Catch:{ all -> 0x00eb }
            java.lang.Object r13 = r1.hasNext(r2)     // Catch:{ all -> 0x00eb }
            if (r13 != r3) goto L_0x0151
            return r3
        L_0x0151:
            r14 = r12
            r12 = r9
            r9 = r1
            r1 = r11
            r11 = r4
            r4 = r0
            r0 = r13
        L_0x0158:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x00eb }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x00eb }
            if (r0 == 0) goto L_0x018d
            r2.L$0 = r14     // Catch:{ all -> 0x00eb }
            r2.L$1 = r1     // Catch:{ all -> 0x00eb }
            r2.L$2 = r10     // Catch:{ all -> 0x00eb }
            r2.L$3 = r12     // Catch:{ all -> 0x00eb }
            r2.L$4 = r11     // Catch:{ all -> 0x00eb }
            r2.L$5 = r9     // Catch:{ all -> 0x00eb }
            r2.L$6 = r4     // Catch:{ all -> 0x00eb }
            r2.L$7 = r4     // Catch:{ all -> 0x00eb }
            r2.L$8 = r1     // Catch:{ all -> 0x00eb }
            r2.label = r5     // Catch:{ all -> 0x00eb }
            java.lang.Object r0 = r9.next(r2)     // Catch:{ all -> 0x00eb }
            if (r0 != r3) goto L_0x017b
            return r3
        L_0x017b:
            r13 = r1
            r16 = r12
            r12 = r10
            r10 = r11
            r11 = r16
        L_0x0182:
            java.lang.Object r0 = r1.invoke(r4, r0)     // Catch:{ all -> 0x005e }
            r1 = r9
            r4 = r10
            r9 = r11
            r10 = r12
            r11 = r13
            r12 = r14
            goto L_0x013a
        L_0x018d:
            kotlin.jvm.internal.InlineMarker.finallyStart(r7)
            r10.cancel(r12)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r7)
            return r4
        L_0x0197:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ all -> 0x00eb }
            java.lang.String r1 = "Empty channel can't be reduced."
            r0.<init>(r1)     // Catch:{ all -> 0x00eb }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x00eb }
            throw r0     // Catch:{ all -> 0x00eb }
        L_0x01a1:
            r0 = move-exception
            r10 = r1
            goto L_0x00ec
        L_0x01a5:
            throw r1     // Catch:{ all -> 0x01a6 }
        L_0x01a6:
            r0 = move-exception
            r2 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r8)
            r10.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r8)
            throw r2
        L_0x01b2:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.reduce(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0134 A[Catch:{ all -> 0x0100 }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x016e A[Catch:{ all -> 0x00d3 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x016f A[Catch:{ all -> 0x00d3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0185 A[Catch:{ all -> 0x00d3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x01d0 A[SYNTHETIC, Splitter:B:83:0x01d0] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <S, E extends S> java.lang.Object reduceIndexed(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r19, kotlin.jvm.functions.Function3<? super java.lang.Integer, ? super S, ? super E, ? extends S> r20, kotlin.coroutines.Continuation<? super S> r21) {
        /*
            r1 = r19
            r0 = r21
            boolean r2 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduceIndexed$1
            if (r2 == 0) goto L_0x0018
            r2 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduceIndexed$1 r2 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduceIndexed$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r0 = r2.label
            int r0 = r0 - r4
            r2.label = r0
            goto L_0x001d
        L_0x0018:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduceIndexed$1 r2 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$reduceIndexed$1
            r2.<init>(r0)
        L_0x001d:
            java.lang.Object r0 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 4
            r6 = 3
            r7 = 2
            r8 = 1
            if (r4 == 0) goto L_0x0105
            if (r4 == r8) goto L_0x00d7
            if (r4 == r7) goto L_0x00ae
            if (r4 == r6) goto L_0x007a
            if (r4 != r5) goto L_0x0072
            java.lang.Object r1 = r2.L$9
            kotlin.jvm.functions.Function3 r1 = (kotlin.jvm.functions.Function3) r1
            java.lang.Object r4 = r2.L$8
            java.lang.Integer r4 = (java.lang.Integer) r4
            java.lang.Object r9 = r2.L$7
            java.lang.Object r10 = r2.L$6
            int r10 = r2.I$0
            java.lang.Object r11 = r2.L$5
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r2.L$4
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r2.L$3
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            java.lang.Object r14 = r2.L$2
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r15 = r2.L$1
            kotlin.jvm.functions.Function3 r15 = (kotlin.jvm.functions.Function3) r15
            java.lang.Object r5 = r2.L$0
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            boolean r6 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x006d }
            if (r6 != 0) goto L_0x0068
            r16 = r5
            r6 = r13
            r13 = r14
            r14 = 4
            r5 = r4
            r4 = r10
            r10 = r12
            r12 = 3
            goto L_0x01b6
        L_0x0068:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x006d }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x006d }
            throw r0     // Catch:{ all -> 0x006d }
        L_0x006d:
            r0 = move-exception
            r1 = r0
            r9 = r14
            goto L_0x01de
        L_0x0072:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x007a:
            java.lang.Object r1 = r2.L$6
            int r4 = r2.I$0
            java.lang.Object r5 = r2.L$5
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r6 = r2.L$4
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r9 = r2.L$3
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            java.lang.Object r10 = r2.L$2
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r2.L$1
            kotlin.jvm.functions.Function3 r11 = (kotlin.jvm.functions.Function3) r11
            java.lang.Object r12 = r2.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a9 }
            if (r13 != 0) goto L_0x00a4
            r13 = r9
            r9 = r10
            r10 = r6
            r6 = r1
            r1 = r11
            r11 = r5
            r5 = r12
            r12 = 3
            goto L_0x017d
        L_0x00a4:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a9 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a9 }
            throw r0     // Catch:{ all -> 0x00a9 }
        L_0x00a9:
            r0 = move-exception
            r1 = r0
            r9 = r10
            goto L_0x01de
        L_0x00ae:
            int r1 = r2.I$0
            java.lang.Object r4 = r2.L$5
            kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
            java.lang.Object r5 = r2.L$4
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r2.L$3
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r9 = r2.L$2
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r2.L$1
            kotlin.jvm.functions.Function3 r10 = (kotlin.jvm.functions.Function3) r10
            java.lang.Object r11 = r2.L$0
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            boolean r12 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00d3 }
            if (r12 != 0) goto L_0x00ce
            goto L_0x0155
        L_0x00ce:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00d3 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00d3 }
            throw r0     // Catch:{ all -> 0x00d3 }
        L_0x00d3:
            r0 = move-exception
        L_0x00d4:
            r1 = r0
            goto L_0x01de
        L_0x00d7:
            java.lang.Object r1 = r2.L$5
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r2.L$4
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r2.L$3
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r6 = r2.L$2
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r9 = r2.L$1
            kotlin.jvm.functions.Function3 r9 = (kotlin.jvm.functions.Function3) r9
            java.lang.Object r10 = r2.L$0
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            boolean r11 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x0100 }
            if (r11 != 0) goto L_0x00fb
            r17 = r9
            r9 = r1
            r1 = r10
            r10 = r5
            r5 = r17
            goto L_0x012c
        L_0x00fb:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x0100 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x0100 }
            throw r0     // Catch:{ all -> 0x0100 }
        L_0x0100:
            r0 = move-exception
            r1 = r0
            r9 = r6
            goto L_0x01de
        L_0x0105:
            boolean r4 = r0 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L_0x01eb
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlinx.coroutines.channels.ChannelIterator r4 = r19.iterator()     // Catch:{ all -> 0x01da }
            r2.L$0 = r1     // Catch:{ all -> 0x01da }
            r5 = r20
            r2.L$1 = r5     // Catch:{ all -> 0x01da }
            r2.L$2 = r1     // Catch:{ all -> 0x01da }
            r2.L$3 = r0     // Catch:{ all -> 0x01da }
            r2.L$4 = r1     // Catch:{ all -> 0x01da }
            r2.L$5 = r4     // Catch:{ all -> 0x01da }
            r2.label = r8     // Catch:{ all -> 0x01da }
            java.lang.Object r6 = r4.hasNext(r2)     // Catch:{ all -> 0x01da }
            if (r6 != r3) goto L_0x0127
            return r3
        L_0x0127:
            r10 = r0
            r9 = r4
            r0 = r6
            r4 = r1
            r6 = r4
        L_0x012c:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0100 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0100 }
            if (r0 == 0) goto L_0x01d0
            r2.L$0 = r1     // Catch:{ all -> 0x0100 }
            r2.L$1 = r5     // Catch:{ all -> 0x0100 }
            r2.L$2 = r6     // Catch:{ all -> 0x0100 }
            r2.L$3 = r10     // Catch:{ all -> 0x0100 }
            r2.L$4 = r4     // Catch:{ all -> 0x0100 }
            r2.L$5 = r9     // Catch:{ all -> 0x0100 }
            r2.I$0 = r8     // Catch:{ all -> 0x0100 }
            r2.label = r7     // Catch:{ all -> 0x0100 }
            java.lang.Object r0 = r9.next(r2)     // Catch:{ all -> 0x0100 }
            if (r0 != r3) goto L_0x014b
            return r3
        L_0x014b:
            r11 = r1
            r1 = 1
            r17 = r5
            r5 = r4
            r4 = r9
            r9 = r6
            r6 = r10
            r10 = r17
        L_0x0155:
            r2.L$0 = r11     // Catch:{ all -> 0x00d3 }
            r2.L$1 = r10     // Catch:{ all -> 0x00d3 }
            r2.L$2 = r9     // Catch:{ all -> 0x00d3 }
            r2.L$3 = r6     // Catch:{ all -> 0x00d3 }
            r2.L$4 = r5     // Catch:{ all -> 0x00d3 }
            r2.L$5 = r4     // Catch:{ all -> 0x00d3 }
            r2.I$0 = r1     // Catch:{ all -> 0x00d3 }
            r2.L$6 = r0     // Catch:{ all -> 0x00d3 }
            r12 = 3
            r2.label = r12     // Catch:{ all -> 0x00d3 }
            java.lang.Object r13 = r4.hasNext(r2)     // Catch:{ all -> 0x00d3 }
            if (r13 != r3) goto L_0x016f
            return r3
        L_0x016f:
            r17 = r6
            r6 = r0
            r0 = r13
            r13 = r17
            r18 = r4
            r4 = r1
            r1 = r10
            r10 = r5
            r5 = r11
            r11 = r18
        L_0x017d:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x00d3 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x00d3 }
            if (r0 == 0) goto L_0x01c6
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4)     // Catch:{ all -> 0x00d3 }
            int r4 = r4 + 1
            r2.L$0 = r5     // Catch:{ all -> 0x00d3 }
            r2.L$1 = r1     // Catch:{ all -> 0x00d3 }
            r2.L$2 = r9     // Catch:{ all -> 0x00d3 }
            r2.L$3 = r13     // Catch:{ all -> 0x00d3 }
            r2.L$4 = r10     // Catch:{ all -> 0x00d3 }
            r2.L$5 = r11     // Catch:{ all -> 0x00d3 }
            r2.I$0 = r4     // Catch:{ all -> 0x00d3 }
            r2.L$6 = r6     // Catch:{ all -> 0x00d3 }
            r2.L$7 = r6     // Catch:{ all -> 0x00d3 }
            r2.L$8 = r0     // Catch:{ all -> 0x00d3 }
            r2.L$9 = r1     // Catch:{ all -> 0x00d3 }
            r14 = 4
            r2.label = r14     // Catch:{ all -> 0x00d3 }
            java.lang.Object r15 = r11.next(r2)     // Catch:{ all -> 0x00d3 }
            if (r15 != r3) goto L_0x01ab
            return r3
        L_0x01ab:
            r16 = r5
            r5 = r0
            r0 = r15
            r15 = r1
            r17 = r9
            r9 = r6
            r6 = r13
            r13 = r17
        L_0x01b6:
            java.lang.Object r0 = r1.invoke(r5, r9, r0)     // Catch:{ all -> 0x01c2 }
            r1 = r4
            r5 = r10
            r4 = r11
            r9 = r13
            r10 = r15
            r11 = r16
            goto L_0x0155
        L_0x01c2:
            r0 = move-exception
            r1 = r0
            r9 = r13
            goto L_0x01de
        L_0x01c6:
            kotlin.jvm.internal.InlineMarker.finallyStart(r7)
            r9.cancel(r13)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r7)
            return r6
        L_0x01d0:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ all -> 0x0100 }
            java.lang.String r1 = "Empty channel can't be reduced."
            r0.<init>(r1)     // Catch:{ all -> 0x0100 }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x0100 }
            throw r0     // Catch:{ all -> 0x0100 }
        L_0x01da:
            r0 = move-exception
            r9 = r1
            goto L_0x00d4
        L_0x01de:
            throw r1     // Catch:{ all -> 0x01df }
        L_0x01df:
            r0 = move-exception
            r2 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r8)
            r9.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r8)
            throw r2
        L_0x01eb:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.reduceIndexed(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d7 A[Catch:{ all -> 0x0132 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d8 A[Catch:{ all -> 0x0132 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f1 A[Catch:{ all -> 0x0132 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object sumBy(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r18, kotlin.jvm.functions.Function1<? super E, java.lang.Integer> r19, kotlin.coroutines.Continuation<? super java.lang.Integer> r20) {
        /*
            r0 = r20
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumBy$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumBy$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumBy$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumBy$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumBy$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00a0
            if (r3 == r5) goto L_0x006c
            if (r3 != r4) goto L_0x0064
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009b }
            if (r13 != 0) goto L_0x005f
            r14 = r8
            r8 = r1
            r1 = r9
            r9 = r2
            r2 = r14
        L_0x0054:
            r15 = r11
            r11 = r3
            r3 = r6
            r6 = r15
            r16 = r10
            r10 = r7
            r7 = r16
            goto L_0x010f
        L_0x005f:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009b }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009b }
            throw r0     // Catch:{ all -> 0x009b }
        L_0x0064:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006c:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x009b }
            if (r13 != 0) goto L_0x0096
            r14 = r9
            r9 = r2
            r2 = r8
            r8 = r14
            goto L_0x00e9
        L_0x0096:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x009b }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x009b }
            throw r0     // Catch:{ all -> 0x009b }
        L_0x009b:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x0138
        L_0x00a0:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0145
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            r3 = 0
            r0.element = r3
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r6 = r18.iterator()     // Catch:{ all -> 0x0134 }
            r7 = r0
            r8 = r1
            r9 = r2
            r10 = r3
            r11 = r6
            r0 = r18
            r1 = r0
            r2 = r1
            r3 = r2
            r6 = r19
        L_0x00bf:
            r8.L$0 = r0     // Catch:{ all -> 0x0132 }
            r8.L$1 = r6     // Catch:{ all -> 0x0132 }
            r8.L$2 = r7     // Catch:{ all -> 0x0132 }
            r8.L$3 = r1     // Catch:{ all -> 0x0132 }
            r8.L$4 = r2     // Catch:{ all -> 0x0132 }
            r8.L$5 = r10     // Catch:{ all -> 0x0132 }
            r8.L$6 = r3     // Catch:{ all -> 0x0132 }
            r8.L$7 = r11     // Catch:{ all -> 0x0132 }
            r8.label = r5     // Catch:{ all -> 0x0132 }
            java.lang.Object r12 = r11.hasNext(r8)     // Catch:{ all -> 0x0132 }
            if (r12 != r9) goto L_0x00d8
            return r9
        L_0x00d8:
            r14 = r12
            r12 = r0
            r0 = r14
            r15 = r8
            r8 = r1
            r1 = r15
            r16 = r6
            r6 = r3
            r3 = r11
            r11 = r16
            r17 = r10
            r10 = r7
            r7 = r17
        L_0x00e9:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0132 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0132 }
            if (r0 == 0) goto L_0x0120
            r1.L$0 = r12     // Catch:{ all -> 0x0132 }
            r1.L$1 = r11     // Catch:{ all -> 0x0132 }
            r1.L$2 = r10     // Catch:{ all -> 0x0132 }
            r1.L$3 = r8     // Catch:{ all -> 0x0132 }
            r1.L$4 = r2     // Catch:{ all -> 0x0132 }
            r1.L$5 = r7     // Catch:{ all -> 0x0132 }
            r1.L$6 = r6     // Catch:{ all -> 0x0132 }
            r1.L$7 = r3     // Catch:{ all -> 0x0132 }
            r1.label = r4     // Catch:{ all -> 0x0132 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0132 }
            if (r0 != r9) goto L_0x010a
            return r9
        L_0x010a:
            r14 = r8
            r8 = r1
            r1 = r14
            goto L_0x0054
        L_0x010f:
            int r13 = r7.element     // Catch:{ all -> 0x0132 }
            java.lang.Object r0 = r6.invoke(r0)     // Catch:{ all -> 0x0132 }
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ all -> 0x0132 }
            int r0 = r0.intValue()     // Catch:{ all -> 0x0132 }
            int r13 = r13 + r0
            r7.element = r13     // Catch:{ all -> 0x0132 }
            r0 = r12
            goto L_0x00bf
        L_0x0120:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0132 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            int r0 = r10.element
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)
            return r0
        L_0x0132:
            r0 = move-exception
            goto L_0x0137
        L_0x0134:
            r0 = move-exception
            r2 = r18
        L_0x0137:
            r1 = r0
        L_0x0138:
            throw r1     // Catch:{ all -> 0x0139 }
        L_0x0139:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x0145:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.sumBy(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00de A[Catch:{ all -> 0x013f }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00df A[Catch:{ all -> 0x013f }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00fc A[Catch:{ all -> 0x013f }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object sumByDouble(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r21, kotlin.jvm.functions.Function1<? super E, java.lang.Double> r22, kotlin.coroutines.Continuation<? super java.lang.Double> r23) {
        /*
            r0 = r23
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumByDouble$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumByDouble$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumByDouble$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumByDouble$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$sumByDouble$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00a6
            if (r3 == r5) goto L_0x0070
            if (r3 != r4) goto L_0x0068
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$DoubleRef r10 = (kotlin.jvm.internal.Ref.DoubleRef) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a1 }
            if (r13 != 0) goto L_0x0063
            r17 = r8
            r8 = r1
            r1 = r9
            r9 = r2
            r2 = r17
        L_0x0056:
            r18 = r11
            r11 = r3
            r3 = r6
            r6 = r18
            r19 = r10
            r10 = r7
            r7 = r19
            goto L_0x011c
        L_0x0063:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a1 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a1 }
            throw r0     // Catch:{ all -> 0x00a1 }
        L_0x0068:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0070:
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$DoubleRef r10 = (kotlin.jvm.internal.Ref.DoubleRef) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            boolean r13 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a1 }
            if (r13 != 0) goto L_0x009c
            r17 = r9
            r9 = r2
            r2 = r8
            r8 = r17
            goto L_0x00f4
        L_0x009c:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a1 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a1 }
            throw r0     // Catch:{ all -> 0x00a1 }
        L_0x00a1:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x0145
        L_0x00a6:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x0152
            kotlin.jvm.internal.Ref$DoubleRef r0 = new kotlin.jvm.internal.Ref$DoubleRef
            r0.<init>()
            r6 = 0
            r0.element = r6
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            kotlinx.coroutines.channels.ChannelIterator r6 = r21.iterator()     // Catch:{ all -> 0x0141 }
            r7 = r0
            r8 = r1
            r9 = r2
            r10 = r3
            r11 = r6
            r0 = r21
            r1 = r0
            r2 = r1
            r3 = r2
            r6 = r22
        L_0x00c6:
            r8.L$0 = r0     // Catch:{ all -> 0x013f }
            r8.L$1 = r6     // Catch:{ all -> 0x013f }
            r8.L$2 = r7     // Catch:{ all -> 0x013f }
            r8.L$3 = r1     // Catch:{ all -> 0x013f }
            r8.L$4 = r2     // Catch:{ all -> 0x013f }
            r8.L$5 = r10     // Catch:{ all -> 0x013f }
            r8.L$6 = r3     // Catch:{ all -> 0x013f }
            r8.L$7 = r11     // Catch:{ all -> 0x013f }
            r8.label = r5     // Catch:{ all -> 0x013f }
            java.lang.Object r12 = r11.hasNext(r8)     // Catch:{ all -> 0x013f }
            if (r12 != r9) goto L_0x00df
            return r9
        L_0x00df:
            r17 = r12
            r12 = r0
            r0 = r17
            r18 = r8
            r8 = r1
            r1 = r18
            r19 = r6
            r6 = r3
            r3 = r11
            r11 = r19
            r20 = r10
            r10 = r7
            r7 = r20
        L_0x00f4:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x013f }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x013f }
            if (r0 == 0) goto L_0x012d
            r1.L$0 = r12     // Catch:{ all -> 0x013f }
            r1.L$1 = r11     // Catch:{ all -> 0x013f }
            r1.L$2 = r10     // Catch:{ all -> 0x013f }
            r1.L$3 = r8     // Catch:{ all -> 0x013f }
            r1.L$4 = r2     // Catch:{ all -> 0x013f }
            r1.L$5 = r7     // Catch:{ all -> 0x013f }
            r1.L$6 = r6     // Catch:{ all -> 0x013f }
            r1.L$7 = r3     // Catch:{ all -> 0x013f }
            r1.label = r4     // Catch:{ all -> 0x013f }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x013f }
            if (r0 != r9) goto L_0x0115
            return r9
        L_0x0115:
            r17 = r8
            r8 = r1
            r1 = r17
            goto L_0x0056
        L_0x011c:
            double r13 = r7.element     // Catch:{ all -> 0x013f }
            java.lang.Object r0 = r6.invoke(r0)     // Catch:{ all -> 0x013f }
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ all -> 0x013f }
            double r15 = r0.doubleValue()     // Catch:{ all -> 0x013f }
            double r13 = r13 + r15
            r7.element = r13     // Catch:{ all -> 0x013f }
            r0 = r12
            goto L_0x00c6
        L_0x012d:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x013f }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            double r0 = r10.element
            java.lang.Double r0 = kotlin.coroutines.jvm.internal.Boxing.boxDouble(r0)
            return r0
        L_0x013f:
            r0 = move-exception
            goto L_0x0144
        L_0x0141:
            r0 = move-exception
            r2 = r21
        L_0x0144:
            r1 = r0
        L_0x0145:
            throw r1     // Catch:{ all -> 0x0146 }
        L_0x0146:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x0152:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.sumByDouble(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final <E> ReceiveChannel<E> requireNoNulls(ReceiveChannel<? extends E> receiveChannel) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        return map$default(receiveChannel, (CoroutineContext) null, new ChannelsKt__Channels_commonKt$requireNoNulls$1(receiveChannel, (Continuation) null), 1, (Object) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e6 A[Catch:{ all -> 0x0148 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e7 A[Catch:{ all -> 0x0148 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0102 A[Catch:{ all -> 0x0148 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x012e A[Catch:{ all -> 0x0148 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0132 A[Catch:{ all -> 0x0148 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> java.lang.Object partition(kotlinx.coroutines.channels.ReceiveChannel<? extends E> r19, kotlin.jvm.functions.Function1<? super E, java.lang.Boolean> r20, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<? extends E>, ? extends java.util.List<? extends E>>> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$partition$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$partition$1 r1 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$partition$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$partition$1 r1 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$partition$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x00aa
            if (r3 == r5) goto L_0x0072
            if (r3 != r4) goto L_0x006a
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            java.util.ArrayList r10 = (java.util.ArrayList) r10
            java.lang.Object r11 = r1.L$2
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a5 }
            if (r14 != 0) goto L_0x0065
            r15 = r8
            r8 = r1
            r1 = r9
            r9 = r2
            r2 = r15
        L_0x0058:
            r16 = r12
            r12 = r3
            r3 = r6
            r6 = r16
            r17 = r11
            r11 = r7
            r7 = r17
            goto L_0x0122
        L_0x0065:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a5 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a5 }
            throw r0     // Catch:{ all -> 0x00a5 }
        L_0x006a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0072:
            java.lang.Object r3 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r3 = (kotlinx.coroutines.channels.ChannelIterator) r3
            java.lang.Object r6 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$6
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$3
            java.util.ArrayList r10 = (java.util.ArrayList) r10
            java.lang.Object r11 = r1.L$2
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            boolean r14 = r0 instanceof kotlin.Result.Failure     // Catch:{ all -> 0x00a5 }
            if (r14 != 0) goto L_0x00a0
            r15 = r9
            r9 = r2
            r2 = r8
            r8 = r15
            goto L_0x00fa
        L_0x00a0:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0     // Catch:{ all -> 0x00a5 }
            java.lang.Throwable r0 = r0.exception     // Catch:{ all -> 0x00a5 }
            throw r0     // Catch:{ all -> 0x00a5 }
        L_0x00a5:
            r0 = move-exception
            r1 = r0
            r2 = r8
            goto L_0x014e
        L_0x00aa:
            boolean r3 = r0 instanceof kotlin.Result.Failure
            if (r3 != 0) goto L_0x015b
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r6 = 0
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r7 = r19.iterator()     // Catch:{ all -> 0x014a }
            r8 = r1
            r9 = r2
            r10 = r3
            r11 = r6
            r12 = r7
            r1 = r19
            r2 = r1
            r3 = r2
            r6 = r20
            r7 = r0
            r0 = r3
        L_0x00cc:
            r8.L$0 = r0     // Catch:{ all -> 0x0148 }
            r8.L$1 = r6     // Catch:{ all -> 0x0148 }
            r8.L$2 = r7     // Catch:{ all -> 0x0148 }
            r8.L$3 = r10     // Catch:{ all -> 0x0148 }
            r8.L$4 = r1     // Catch:{ all -> 0x0148 }
            r8.L$5 = r2     // Catch:{ all -> 0x0148 }
            r8.L$6 = r11     // Catch:{ all -> 0x0148 }
            r8.L$7 = r3     // Catch:{ all -> 0x0148 }
            r8.L$8 = r12     // Catch:{ all -> 0x0148 }
            r8.label = r5     // Catch:{ all -> 0x0148 }
            java.lang.Object r13 = r12.hasNext(r8)     // Catch:{ all -> 0x0148 }
            if (r13 != r9) goto L_0x00e7
            return r9
        L_0x00e7:
            r15 = r13
            r13 = r0
            r0 = r15
            r16 = r8
            r8 = r1
            r1 = r16
            r17 = r6
            r6 = r3
            r3 = r12
            r12 = r17
            r18 = r11
            r11 = r7
            r7 = r18
        L_0x00fa:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0148 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0148 }
            if (r0 == 0) goto L_0x0137
            r1.L$0 = r13     // Catch:{ all -> 0x0148 }
            r1.L$1 = r12     // Catch:{ all -> 0x0148 }
            r1.L$2 = r11     // Catch:{ all -> 0x0148 }
            r1.L$3 = r10     // Catch:{ all -> 0x0148 }
            r1.L$4 = r8     // Catch:{ all -> 0x0148 }
            r1.L$5 = r2     // Catch:{ all -> 0x0148 }
            r1.L$6 = r7     // Catch:{ all -> 0x0148 }
            r1.L$7 = r6     // Catch:{ all -> 0x0148 }
            r1.L$8 = r3     // Catch:{ all -> 0x0148 }
            r1.label = r4     // Catch:{ all -> 0x0148 }
            java.lang.Object r0 = r3.next(r1)     // Catch:{ all -> 0x0148 }
            if (r0 != r9) goto L_0x011d
            return r9
        L_0x011d:
            r15 = r8
            r8 = r1
            r1 = r15
            goto L_0x0058
        L_0x0122:
            java.lang.Object r14 = r6.invoke(r0)     // Catch:{ all -> 0x0148 }
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ all -> 0x0148 }
            boolean r14 = r14.booleanValue()     // Catch:{ all -> 0x0148 }
            if (r14 == 0) goto L_0x0132
            r7.add(r0)     // Catch:{ all -> 0x0148 }
            goto L_0x0135
        L_0x0132:
            r10.add(r0)     // Catch:{ all -> 0x0148 }
        L_0x0135:
            r0 = r13
            goto L_0x00cc
        L_0x0137:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0148 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            kotlin.Pair r0 = new kotlin.Pair
            r0.<init>(r11, r10)
            return r0
        L_0x0148:
            r0 = move-exception
            goto L_0x014d
        L_0x014a:
            r0 = move-exception
            r2 = r19
        L_0x014d:
            r1 = r0
        L_0x014e:
            throw r1     // Catch:{ all -> 0x014f }
        L_0x014f:
            r0 = move-exception
            r3 = r0
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            r2.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            throw r3
        L_0x015b:
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.partition(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0056, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0057, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r7.cancel(r8);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0060, code lost:
        throw r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object partition$$forInline(kotlinx.coroutines.channels.ReceiveChannel r7, kotlin.jvm.functions.Function1 r8, kotlin.coroutines.Continuation r9) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            r3 = 1
            kotlinx.coroutines.channels.ChannelIterator r4 = r7.iterator()     // Catch:{ all -> 0x0054 }
        L_0x0012:
            r5 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r5)     // Catch:{ all -> 0x0054 }
            java.lang.Object r6 = r4.hasNext(r9)     // Catch:{ all -> 0x0054 }
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0054 }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x0054 }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x0054 }
            if (r6 == 0) goto L_0x0043
            kotlin.jvm.internal.InlineMarker.mark((int) r5)     // Catch:{ all -> 0x0054 }
            java.lang.Object r5 = r4.next(r9)     // Catch:{ all -> 0x0054 }
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0054 }
            java.lang.Object r6 = r8.invoke(r5)     // Catch:{ all -> 0x0054 }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x0054 }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x0054 }
            if (r6 == 0) goto L_0x003f
            r0.add(r5)     // Catch:{ all -> 0x0054 }
            goto L_0x0012
        L_0x003f:
            r1.add(r5)     // Catch:{ all -> 0x0054 }
            goto L_0x0012
        L_0x0043:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0054 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            r7.cancel(r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            kotlin.Pair r7 = new kotlin.Pair
            r7.<init>(r0, r1)
            return r7
        L_0x0054:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0056 }
        L_0x0056:
            r9 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            r7.cancel(r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.partition$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final <E, R> ReceiveChannel<Pair<E, R>> zip(ReceiveChannel<? extends E> receiveChannel, ReceiveChannel<? extends R> receiveChannel2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(receiveChannel2, "other");
        return zip$default(receiveChannel, receiveChannel2, (CoroutineContext) null, ChannelsKt__Channels_commonKt$zip$1.INSTANCE, 2, (Object) null);
    }

    public static /* synthetic */ ReceiveChannel zip$default(ReceiveChannel receiveChannel, ReceiveChannel receiveChannel2, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.zip(receiveChannel, receiveChannel2, coroutineContext, function2);
    }

    public static final <E, R, V> ReceiveChannel<V> zip(ReceiveChannel<? extends E> receiveChannel, ReceiveChannel<? extends R> receiveChannel2, CoroutineContext coroutineContext, Function2<? super E, ? super R, ? extends V> function2) {
        Intrinsics.checkParameterIsNotNull(receiveChannel, "receiver$0");
        Intrinsics.checkParameterIsNotNull(receiveChannel2, "other");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, ChannelsKt.consumesAll(receiveChannel, receiveChannel2), new ChannelsKt__Channels_commonKt$zip$2(receiveChannel, receiveChannel2, function2, (Continuation) null), 2, (Object) null);
    }

    private static final Object consumeEach$$forInline(BroadcastChannel broadcastChannel, Function1 function1, Continuation continuation) {
        ReceiveChannel openSubscription = broadcastChannel.openSubscription();
        try {
            ChannelIterator it = openSubscription.iterator();
            while (true) {
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(continuation);
                InlineMarker.mark(1);
                if (!((Boolean) hasNext).booleanValue()) {
                    return Unit.INSTANCE;
                }
                InlineMarker.mark(0);
                Object next = it.next(continuation);
                InlineMarker.mark(1);
                function1.invoke(next);
            }
        } finally {
            InlineMarker.finallyStart(1);
            openSubscription.cancel();
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0037, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0038, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r5.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0041, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object consumeEach$$forInline(kotlinx.coroutines.channels.ReceiveChannel r5, kotlin.jvm.functions.Function1 r6, kotlin.coroutines.Continuation r7) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r5.iterator()     // Catch:{ all -> 0x0035 }
        L_0x0008:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0035 }
            java.lang.Object r4 = r2.hasNext(r7)     // Catch:{ all -> 0x0035 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0035 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0035 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0035 }
            if (r4 == 0) goto L_0x0029
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0035 }
            java.lang.Object r3 = r2.next(r7)     // Catch:{ all -> 0x0035 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0035 }
            r6.invoke(r3)     // Catch:{ all -> 0x0035 }
            goto L_0x0008
        L_0x0029:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0035 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r6
        L_0x0035:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0037 }
        L_0x0037:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeEach$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0042, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0043, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r8.cancel(r9);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004c, code lost:
        throw r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object consumeEachIndexed$$forInline(kotlinx.coroutines.channels.ReceiveChannel r8, kotlin.jvm.functions.Function1 r9, kotlin.coroutines.Continuation r10) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r8.iterator()     // Catch:{ all -> 0x0040 }
            r3 = 0
            r4 = 0
        L_0x000a:
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0040 }
            java.lang.Object r5 = r2.hasNext(r10)     // Catch:{ all -> 0x0040 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0040 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0040 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0040 }
            if (r5 == 0) goto L_0x0032
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0040 }
            java.lang.Object r5 = r2.next(r10)     // Catch:{ all -> 0x0040 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0040 }
            kotlin.collections.IndexedValue r6 = new kotlin.collections.IndexedValue     // Catch:{ all -> 0x0040 }
            int r7 = r4 + 1
            r6.<init>(r4, r5)     // Catch:{ all -> 0x0040 }
            r9.invoke(r6)     // Catch:{ all -> 0x0040 }
            r4 = r7
            goto L_0x000a
        L_0x0032:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0040 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r8.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0040:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0042 }
        L_0x0042:
            r10 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r8.cancel(r9)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.consumeEachIndexed$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r8 = r9.invoke(java.lang.Integer.valueOf(r8));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0054, code lost:
        r9 = 2;
        kotlin.jvm.internal.InlineMarker.finallyStart(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005b, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005c, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r7.cancel(r8);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0065, code lost:
        throw r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object elementAtOrElse$$forInline(kotlinx.coroutines.channels.ReceiveChannel r7, int r8, kotlin.jvm.functions.Function1 r9, kotlin.coroutines.Continuation r10) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            if (r8 >= 0) goto L_0x0019
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0059 }
            java.lang.Object r8 = r9.invoke(r8)     // Catch:{ all -> 0x0059 }
            r9 = 4
            kotlin.jvm.internal.InlineMarker.finallyStart(r9)
        L_0x0012:
            r7.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r9)
            return r8
        L_0x0019:
            kotlinx.coroutines.channels.ChannelIterator r2 = r7.iterator()     // Catch:{ all -> 0x0059 }
            r3 = 0
            r4 = 0
        L_0x001f:
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0059 }
            java.lang.Object r5 = r2.hasNext(r10)     // Catch:{ all -> 0x0059 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0059 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0059 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0059 }
            if (r5 == 0) goto L_0x004c
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0059 }
            java.lang.Object r5 = r2.next(r10)     // Catch:{ all -> 0x0059 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0059 }
            int r6 = r4 + 1
            if (r8 != r4) goto L_0x004a
            r8 = 3
            kotlin.jvm.internal.InlineMarker.finallyStart(r8)
            r7.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r8)
            return r5
        L_0x004a:
            r4 = r6
            goto L_0x001f
        L_0x004c:
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0059 }
            java.lang.Object r8 = r9.invoke(r8)     // Catch:{ all -> 0x0059 }
            r9 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r9)
            goto L_0x0012
        L_0x0059:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x005b }
        L_0x005b:
            r9 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r7.cancel(r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.elementAtOrElse$$forInline(kotlinx.coroutines.channels.ReceiveChannel, int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004c, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004d, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r6.cancel(r7);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0056, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object find$$forInline(kotlinx.coroutines.channels.ReceiveChannel r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            r0 = 0
            r1 = r0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = 1
            kotlinx.coroutines.channels.ChannelIterator r3 = r6.iterator()     // Catch:{ all -> 0x004a }
        L_0x0009:
            r4 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x004a }
            java.lang.Object r5 = r3.hasNext(r8)     // Catch:{ all -> 0x004a }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x004a }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x004a }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x004a }
            if (r5 == 0) goto L_0x003e
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x004a }
            java.lang.Object r4 = r3.next(r8)     // Catch:{ all -> 0x004a }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x004a }
            java.lang.Object r5 = r7.invoke(r4)     // Catch:{ all -> 0x004a }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x004a }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x004a }
            if (r5 == 0) goto L_0x0009
            r7 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r7)
            r6.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r7)
            r0 = r4
            goto L_0x0049
        L_0x003e:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x004a }
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r6.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
        L_0x0049:
            return r0
        L_0x004a:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x004c }
        L_0x004c:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r6.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.find$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0042, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0043, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r6.cancel(r7);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004c, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object findLast$$forInline(kotlinx.coroutines.channels.ReceiveChannel r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            r0 = 0
            r1 = r0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = 1
            kotlinx.coroutines.channels.ChannelIterator r3 = r6.iterator()     // Catch:{ all -> 0x0040 }
        L_0x0009:
            r4 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0040 }
            java.lang.Object r5 = r3.hasNext(r8)     // Catch:{ all -> 0x0040 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0040 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0040 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0040 }
            if (r5 == 0) goto L_0x0034
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0040 }
            java.lang.Object r4 = r3.next(r8)     // Catch:{ all -> 0x0040 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0040 }
            java.lang.Object r5 = r7.invoke(r4)     // Catch:{ all -> 0x0040 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0040 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0040 }
            if (r5 == 0) goto L_0x0009
            r0 = r4
            goto L_0x0009
        L_0x0034:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0040 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r6.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            return r0
        L_0x0040:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0042 }
        L_0x0042:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r6.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.findLast$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0053, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0054, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r5.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005d, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object first$$forInline(kotlinx.coroutines.channels.ReceiveChannel r5, kotlin.jvm.functions.Function1 r6, kotlin.coroutines.Continuation r7) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r5.iterator()     // Catch:{ all -> 0x0051 }
        L_0x0008:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0051 }
            java.lang.Object r4 = r2.hasNext(r7)     // Catch:{ all -> 0x0051 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0051 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0051 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0051 }
            if (r4 == 0) goto L_0x003c
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0051 }
            java.lang.Object r3 = r2.next(r7)     // Catch:{ all -> 0x0051 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0051 }
            java.lang.Object r4 = r6.invoke(r3)     // Catch:{ all -> 0x0051 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0051 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0051 }
            if (r4 == 0) goto L_0x0008
            r6 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            r5.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            return r3
        L_0x003c:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0051 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            java.util.NoSuchElementException r5 = new java.util.NoSuchElementException
            java.lang.String r6 = "ReceiveChannel contains no element matching the predicate."
            r5.<init>(r6)
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            throw r5
        L_0x0051:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0053 }
        L_0x0053:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.first$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004b, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004c, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r6.cancel(r7);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0055, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object firstOrNull$$forInline(kotlinx.coroutines.channels.ReceiveChannel r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            r0 = 0
            r1 = r0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = 1
            kotlinx.coroutines.channels.ChannelIterator r3 = r6.iterator()     // Catch:{ all -> 0x0049 }
        L_0x0009:
            r4 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0049 }
            java.lang.Object r5 = r3.hasNext(r8)     // Catch:{ all -> 0x0049 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0049 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0049 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0049 }
            if (r5 == 0) goto L_0x003d
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0049 }
            java.lang.Object r4 = r3.next(r8)     // Catch:{ all -> 0x0049 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0049 }
            java.lang.Object r5 = r7.invoke(r4)     // Catch:{ all -> 0x0049 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0049 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0049 }
            if (r5 == 0) goto L_0x0009
            r7 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r7)
            r6.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r7)
            return r4
        L_0x003d:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0049 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r6.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            return r0
        L_0x0049:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x004b }
        L_0x004b:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r6.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.firstOrNull$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0057, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0058, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r6.cancel(r7);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0061, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object indexOfFirst$$forInline(kotlinx.coroutines.channels.ReceiveChannel r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r6.iterator()     // Catch:{ all -> 0x0055 }
            r3 = 0
            r4 = 0
        L_0x000a:
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0055 }
            java.lang.Object r5 = r2.hasNext(r8)     // Catch:{ all -> 0x0055 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0055 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0055 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0055 }
            if (r5 == 0) goto L_0x0044
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0055 }
            java.lang.Object r5 = r2.next(r8)     // Catch:{ all -> 0x0055 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0055 }
            java.lang.Object r5 = r7.invoke(r5)     // Catch:{ all -> 0x0055 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0055 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0055 }
            if (r5 == 0) goto L_0x0041
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0055 }
            r8 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r8)
            r6.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r8)
            return r7
        L_0x0041:
            int r4 = r4 + 1
            goto L_0x000a
        L_0x0044:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0055 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r6.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            r6 = -1
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            return r6
        L_0x0055:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0057 }
        L_0x0057:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r6.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.indexOfFirst$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0048, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0049, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r7.cancel(r8);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0052, code lost:
        throw r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object indexOfLast$$forInline(kotlinx.coroutines.channels.ReceiveChannel r7, kotlin.jvm.functions.Function1 r8, kotlin.coroutines.Continuation r9) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r7.iterator()     // Catch:{ all -> 0x0046 }
            r3 = 0
            r4 = -1
            r5 = 0
        L_0x000b:
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0046 }
            java.lang.Object r6 = r2.hasNext(r9)     // Catch:{ all -> 0x0046 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0046 }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x0046 }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x0046 }
            if (r6 == 0) goto L_0x0036
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0046 }
            java.lang.Object r6 = r2.next(r9)     // Catch:{ all -> 0x0046 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0046 }
            java.lang.Object r6 = r8.invoke(r6)     // Catch:{ all -> 0x0046 }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x0046 }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x0046 }
            if (r6 == 0) goto L_0x0034
            r4 = r5
        L_0x0034:
            int r5 = r5 + r1
            goto L_0x000b
        L_0x0036:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0046 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r7.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)
            return r7
        L_0x0046:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0048 }
        L_0x0048:
            r9 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r7.cancel(r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.indexOfLast$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0050, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0051, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r8.cancel(r9);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005a, code lost:
        throw r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object last$$forInline(kotlinx.coroutines.channels.ReceiveChannel r8, kotlin.jvm.functions.Function1 r9, kotlin.coroutines.Continuation r10) {
        /*
            r0 = 0
            r1 = r0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = 1
            kotlinx.coroutines.channels.ChannelIterator r3 = r8.iterator()     // Catch:{ all -> 0x004e }
            r4 = 0
            r5 = 0
        L_0x000b:
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x004e }
            java.lang.Object r6 = r3.hasNext(r10)     // Catch:{ all -> 0x004e }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x004e }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x004e }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x004e }
            if (r6 == 0) goto L_0x0036
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x004e }
            java.lang.Object r6 = r3.next(r10)     // Catch:{ all -> 0x004e }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x004e }
            java.lang.Object r7 = r9.invoke(r6)     // Catch:{ all -> 0x004e }
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ all -> 0x004e }
            boolean r7 = r7.booleanValue()     // Catch:{ all -> 0x004e }
            if (r7 == 0) goto L_0x000b
            r0 = r6
            r5 = 1
            goto L_0x000b
        L_0x0036:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x004e }
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r8.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            if (r5 == 0) goto L_0x0044
            return r0
        L_0x0044:
            java.util.NoSuchElementException r8 = new java.util.NoSuchElementException
            java.lang.String r9 = "ReceiveChannel contains no element matching the predicate."
            r8.<init>(r9)
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            throw r8
        L_0x004e:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0050 }
        L_0x0050:
            r10 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r8.cancel(r9)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.last$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0042, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0043, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r6.cancel(r7);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004c, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object lastOrNull$$forInline(kotlinx.coroutines.channels.ReceiveChannel r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            r0 = 0
            r1 = r0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = 1
            kotlinx.coroutines.channels.ChannelIterator r3 = r6.iterator()     // Catch:{ all -> 0x0040 }
        L_0x0009:
            r4 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0040 }
            java.lang.Object r5 = r3.hasNext(r8)     // Catch:{ all -> 0x0040 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0040 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0040 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0040 }
            if (r5 == 0) goto L_0x0034
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0040 }
            java.lang.Object r4 = r3.next(r8)     // Catch:{ all -> 0x0040 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0040 }
            java.lang.Object r5 = r7.invoke(r4)     // Catch:{ all -> 0x0040 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0040 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0040 }
            if (r5 == 0) goto L_0x0009
            r0 = r4
            goto L_0x0009
        L_0x0034:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0040 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r6.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            return r0
        L_0x0040:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0042 }
        L_0x0042:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r6.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.lastOrNull$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005c, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005d, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r8.cancel(r9);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0066, code lost:
        throw r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object single$$forInline(kotlinx.coroutines.channels.ReceiveChannel r8, kotlin.jvm.functions.Function1 r9, kotlin.coroutines.Continuation r10) {
        /*
            r0 = 0
            r1 = r0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = 1
            kotlinx.coroutines.channels.ChannelIterator r3 = r8.iterator()     // Catch:{ all -> 0x005a }
            r4 = 0
            r5 = 0
        L_0x000b:
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x005a }
            java.lang.Object r6 = r3.hasNext(r10)     // Catch:{ all -> 0x005a }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x005a }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x005a }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x005a }
            if (r6 == 0) goto L_0x0042
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x005a }
            java.lang.Object r6 = r3.next(r10)     // Catch:{ all -> 0x005a }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x005a }
            java.lang.Object r7 = r9.invoke(r6)     // Catch:{ all -> 0x005a }
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ all -> 0x005a }
            boolean r7 = r7.booleanValue()     // Catch:{ all -> 0x005a }
            if (r7 == 0) goto L_0x000b
            if (r5 != 0) goto L_0x0038
            r0 = r6
            r5 = 1
            goto L_0x000b
        L_0x0038:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x005a }
            java.lang.String r10 = "ReceiveChannel contains more than one matching element."
            r9.<init>(r10)     // Catch:{ all -> 0x005a }
            java.lang.Throwable r9 = (java.lang.Throwable) r9     // Catch:{ all -> 0x005a }
            throw r9     // Catch:{ all -> 0x005a }
        L_0x0042:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x005a }
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r8.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            if (r5 == 0) goto L_0x0050
            return r0
        L_0x0050:
            java.util.NoSuchElementException r8 = new java.util.NoSuchElementException
            java.lang.String r9 = "ReceiveChannel contains no element matching the predicate."
            r8.<init>(r9)
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            throw r8
        L_0x005a:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x005c }
        L_0x005c:
            r10 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r8.cancel(r9)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.single$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r10 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r9.cancel(r1);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004f, code lost:
        if (r5 != false) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0051, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0052, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0055, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0056, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r9.cancel(r10);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005f, code lost:
        throw r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object singleOrNull$$forInline(kotlinx.coroutines.channels.ReceiveChannel r9, kotlin.jvm.functions.Function1 r10, kotlin.coroutines.Continuation r11) {
        /*
            r0 = 0
            r1 = r0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = 1
            kotlinx.coroutines.channels.ChannelIterator r3 = r9.iterator()     // Catch:{ all -> 0x0053 }
            r4 = 0
            r6 = r0
            r5 = 0
        L_0x000c:
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0053 }
            java.lang.Object r7 = r3.hasNext(r11)     // Catch:{ all -> 0x0053 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0053 }
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ all -> 0x0053 }
            boolean r7 = r7.booleanValue()     // Catch:{ all -> 0x0053 }
            if (r7 == 0) goto L_0x0044
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0053 }
            java.lang.Object r7 = r3.next(r11)     // Catch:{ all -> 0x0053 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0053 }
            java.lang.Object r8 = r10.invoke(r7)     // Catch:{ all -> 0x0053 }
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x0053 }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x0053 }
            if (r8 == 0) goto L_0x000c
            if (r5 == 0) goto L_0x0041
            r10 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r10)
            r9.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r10)
            return r0
        L_0x0041:
            r6 = r7
            r5 = 1
            goto L_0x000c
        L_0x0044:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0053 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r9.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            if (r5 != 0) goto L_0x0052
            return r0
        L_0x0052:
            return r6
        L_0x0053:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x0055 }
        L_0x0055:
            r11 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r9.cancel(r10)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.singleOrNull$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0058, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0059, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r8.cancel(r9);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0062, code lost:
        throw r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object filterIndexedTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel r8, java.util.Collection r9, kotlin.jvm.functions.Function2 r10, kotlin.coroutines.Continuation r11) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r8.iterator()     // Catch:{ all -> 0x0056 }
            r3 = 0
            r4 = 0
        L_0x000a:
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0056 }
            java.lang.Object r5 = r2.hasNext(r11)     // Catch:{ all -> 0x0056 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0056 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0056 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0056 }
            if (r5 == 0) goto L_0x004a
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0056 }
            java.lang.Object r5 = r2.next(r11)     // Catch:{ all -> 0x0056 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0056 }
            kotlin.collections.IndexedValue r6 = new kotlin.collections.IndexedValue     // Catch:{ all -> 0x0056 }
            int r7 = r4 + 1
            r6.<init>(r4, r5)     // Catch:{ all -> 0x0056 }
            int r4 = r6.component1()     // Catch:{ all -> 0x0056 }
            java.lang.Object r5 = r6.component2()     // Catch:{ all -> 0x0056 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0056 }
            java.lang.Object r4 = r10.invoke(r4, r5)     // Catch:{ all -> 0x0056 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0056 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0056 }
            if (r4 == 0) goto L_0x0048
            r9.add(r5)     // Catch:{ all -> 0x0056 }
        L_0x0048:
            r4 = r7
            goto L_0x000a
        L_0x004a:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0056 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r8.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r9
        L_0x0056:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0058 }
        L_0x0058:
            r10 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r8.cancel(r9)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterIndexedTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0062, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0063, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r8.cancel(r9);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x006c, code lost:
        throw r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object filterIndexedTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel r8, kotlinx.coroutines.channels.SendChannel r9, kotlin.jvm.functions.Function2 r10, kotlin.coroutines.Continuation r11) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r8.iterator()     // Catch:{ all -> 0x0060 }
            r3 = 0
            r4 = 0
        L_0x000a:
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0060 }
            java.lang.Object r5 = r2.hasNext(r11)     // Catch:{ all -> 0x0060 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0060 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0060 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0060 }
            if (r5 == 0) goto L_0x0054
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0060 }
            java.lang.Object r5 = r2.next(r11)     // Catch:{ all -> 0x0060 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0060 }
            kotlin.collections.IndexedValue r6 = new kotlin.collections.IndexedValue     // Catch:{ all -> 0x0060 }
            int r7 = r4 + 1
            r6.<init>(r4, r5)     // Catch:{ all -> 0x0060 }
            int r4 = r6.component1()     // Catch:{ all -> 0x0060 }
            java.lang.Object r5 = r6.component2()     // Catch:{ all -> 0x0060 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0060 }
            java.lang.Object r4 = r10.invoke(r4, r5)     // Catch:{ all -> 0x0060 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0060 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0060 }
            if (r4 == 0) goto L_0x0052
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0060 }
            r9.send(r5, r11)     // Catch:{ all -> 0x0060 }
            r4 = 2
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0060 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0060 }
        L_0x0052:
            r4 = r7
            goto L_0x000a
        L_0x0054:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0060 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r8.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r9
        L_0x0060:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0062 }
        L_0x0062:
            r10 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r8.cancel(r9)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterIndexedTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0043, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0044, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r5.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004d, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object filterNotTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel r5, java.util.Collection r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r5.iterator()     // Catch:{ all -> 0x0041 }
        L_0x0008:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0041 }
            java.lang.Object r4 = r2.hasNext(r8)     // Catch:{ all -> 0x0041 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0041 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0041 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0041 }
            if (r4 == 0) goto L_0x0035
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0041 }
            java.lang.Object r3 = r2.next(r8)     // Catch:{ all -> 0x0041 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0041 }
            java.lang.Object r4 = r7.invoke(r3)     // Catch:{ all -> 0x0041 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0041 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0041 }
            if (r4 != 0) goto L_0x0008
            r6.add(r3)     // Catch:{ all -> 0x0041 }
            goto L_0x0008
        L_0x0035:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0041 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r6
        L_0x0041:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0043 }
        L_0x0043:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterNotTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004d, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004e, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r6.cancel(r7);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0057, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object filterNotTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel r6, kotlinx.coroutines.channels.SendChannel r7, kotlin.jvm.functions.Function1 r8, kotlin.coroutines.Continuation r9) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r6.iterator()     // Catch:{ all -> 0x004b }
        L_0x0008:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x004b }
            java.lang.Object r4 = r2.hasNext(r9)     // Catch:{ all -> 0x004b }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x004b }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x004b }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x004b }
            if (r4 == 0) goto L_0x003f
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x004b }
            java.lang.Object r4 = r2.next(r9)     // Catch:{ all -> 0x004b }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x004b }
            java.lang.Object r5 = r8.invoke(r4)     // Catch:{ all -> 0x004b }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x004b }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x004b }
            if (r5 != 0) goto L_0x0008
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x004b }
            r7.send(r4, r9)     // Catch:{ all -> 0x004b }
            r3 = 2
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x004b }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x004b }
            goto L_0x0008
        L_0x003f:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x004b }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r6.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r7
        L_0x004b:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x004d }
        L_0x004d:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r6.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterNotTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0043, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0044, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r5.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004d, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object filterTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel r5, java.util.Collection r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r5.iterator()     // Catch:{ all -> 0x0041 }
        L_0x0008:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0041 }
            java.lang.Object r4 = r2.hasNext(r8)     // Catch:{ all -> 0x0041 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0041 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0041 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0041 }
            if (r4 == 0) goto L_0x0035
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0041 }
            java.lang.Object r3 = r2.next(r8)     // Catch:{ all -> 0x0041 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0041 }
            java.lang.Object r4 = r7.invoke(r3)     // Catch:{ all -> 0x0041 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0041 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0041 }
            if (r4 == 0) goto L_0x0008
            r6.add(r3)     // Catch:{ all -> 0x0041 }
            goto L_0x0008
        L_0x0035:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0041 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r6
        L_0x0041:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0043 }
        L_0x0043:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004d, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004e, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r6.cancel(r7);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0057, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object filterTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel r6, kotlinx.coroutines.channels.SendChannel r7, kotlin.jvm.functions.Function1 r8, kotlin.coroutines.Continuation r9) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r6.iterator()     // Catch:{ all -> 0x004b }
        L_0x0008:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x004b }
            java.lang.Object r4 = r2.hasNext(r9)     // Catch:{ all -> 0x004b }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x004b }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x004b }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x004b }
            if (r4 == 0) goto L_0x003f
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x004b }
            java.lang.Object r4 = r2.next(r9)     // Catch:{ all -> 0x004b }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x004b }
            java.lang.Object r5 = r8.invoke(r4)     // Catch:{ all -> 0x004b }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x004b }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x004b }
            if (r5 == 0) goto L_0x0008
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x004b }
            r7.send(r4, r9)     // Catch:{ all -> 0x004b }
            r3 = 2
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x004b }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x004b }
            goto L_0x0008
        L_0x003f:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x004b }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r6.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r7
        L_0x004b:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x004d }
        L_0x004d:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r6.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.filterTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003b, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003c, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r5.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0045, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object associateByTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel r5, java.util.Map r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r5.iterator()     // Catch:{ all -> 0x0039 }
        L_0x0008:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0039 }
            java.lang.Object r4 = r2.hasNext(r8)     // Catch:{ all -> 0x0039 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0039 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0039 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0039 }
            if (r4 == 0) goto L_0x002d
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0039 }
            java.lang.Object r3 = r2.next(r8)     // Catch:{ all -> 0x0039 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0039 }
            java.lang.Object r4 = r7.invoke(r3)     // Catch:{ all -> 0x0039 }
            r6.put(r4, r3)     // Catch:{ all -> 0x0039 }
            goto L_0x0008
        L_0x002d:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0039 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r6
        L_0x0039:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x003b }
        L_0x003b:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateByTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003f, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0040, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r5.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0049, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object associateByTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel r5, java.util.Map r6, kotlin.jvm.functions.Function1 r7, kotlin.jvm.functions.Function1 r8, kotlin.coroutines.Continuation r9) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r5.iterator()     // Catch:{ all -> 0x003d }
        L_0x0008:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x003d }
            java.lang.Object r4 = r2.hasNext(r9)     // Catch:{ all -> 0x003d }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x003d }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x003d }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x003d }
            if (r4 == 0) goto L_0x0031
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x003d }
            java.lang.Object r3 = r2.next(r9)     // Catch:{ all -> 0x003d }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x003d }
            java.lang.Object r4 = r7.invoke(r3)     // Catch:{ all -> 0x003d }
            java.lang.Object r3 = r8.invoke(r3)     // Catch:{ all -> 0x003d }
            r6.put(r4, r3)     // Catch:{ all -> 0x003d }
            goto L_0x0008
        L_0x0031:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x003d }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r6
        L_0x003d:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x003f }
        L_0x003f:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateByTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0045, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r5.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004f, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object associateTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel r5, java.util.Map r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r5.iterator()     // Catch:{ all -> 0x0043 }
        L_0x0008:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0043 }
            java.lang.Object r4 = r2.hasNext(r8)     // Catch:{ all -> 0x0043 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0043 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0043 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0043 }
            if (r4 == 0) goto L_0x0037
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0043 }
            java.lang.Object r3 = r2.next(r8)     // Catch:{ all -> 0x0043 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0043 }
            java.lang.Object r3 = r7.invoke(r3)     // Catch:{ all -> 0x0043 }
            kotlin.Pair r3 = (kotlin.Pair) r3     // Catch:{ all -> 0x0043 }
            java.lang.Object r4 = r3.getFirst()     // Catch:{ all -> 0x0043 }
            java.lang.Object r3 = r3.getSecond()     // Catch:{ all -> 0x0043 }
            r6.put(r4, r3)     // Catch:{ all -> 0x0043 }
            goto L_0x0008
        L_0x0037:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0043 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r6
        L_0x0043:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.associateTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004b, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004c, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r6.cancel(r7);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0055, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object groupByTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel r6, java.util.Map r7, kotlin.jvm.functions.Function1 r8, kotlin.coroutines.Continuation r9) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r6.iterator()     // Catch:{ all -> 0x0049 }
        L_0x0008:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0049 }
            java.lang.Object r4 = r2.hasNext(r9)     // Catch:{ all -> 0x0049 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0049 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0049 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0049 }
            if (r4 == 0) goto L_0x003d
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0049 }
            java.lang.Object r3 = r2.next(r9)     // Catch:{ all -> 0x0049 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0049 }
            java.lang.Object r4 = r8.invoke(r3)     // Catch:{ all -> 0x0049 }
            java.lang.Object r5 = r7.get(r4)     // Catch:{ all -> 0x0049 }
            if (r5 != 0) goto L_0x0037
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x0049 }
            r5.<init>()     // Catch:{ all -> 0x0049 }
            r7.put(r4, r5)     // Catch:{ all -> 0x0049 }
        L_0x0037:
            java.util.List r5 = (java.util.List) r5     // Catch:{ all -> 0x0049 }
            r5.add(r3)     // Catch:{ all -> 0x0049 }
            goto L_0x0008
        L_0x003d:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0049 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r6.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r7
        L_0x0049:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x004b }
        L_0x004b:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r6.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.groupByTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004f, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0050, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r6.cancel(r7);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0059, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object groupByTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel r6, java.util.Map r7, kotlin.jvm.functions.Function1 r8, kotlin.jvm.functions.Function1 r9, kotlin.coroutines.Continuation r10) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r6.iterator()     // Catch:{ all -> 0x004d }
        L_0x0008:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x004d }
            java.lang.Object r4 = r2.hasNext(r10)     // Catch:{ all -> 0x004d }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x004d }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x004d }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x004d }
            if (r4 == 0) goto L_0x0041
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x004d }
            java.lang.Object r3 = r2.next(r10)     // Catch:{ all -> 0x004d }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x004d }
            java.lang.Object r4 = r8.invoke(r3)     // Catch:{ all -> 0x004d }
            java.lang.Object r5 = r7.get(r4)     // Catch:{ all -> 0x004d }
            if (r5 != 0) goto L_0x0037
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x004d }
            r5.<init>()     // Catch:{ all -> 0x004d }
            r7.put(r4, r5)     // Catch:{ all -> 0x004d }
        L_0x0037:
            java.util.List r5 = (java.util.List) r5     // Catch:{ all -> 0x004d }
            java.lang.Object r3 = r9.invoke(r3)     // Catch:{ all -> 0x004d }
            r5.add(r3)     // Catch:{ all -> 0x004d }
            goto L_0x0008
        L_0x0041:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x004d }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r6.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r7
        L_0x004d:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x004f }
        L_0x004f:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r6.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.groupByTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel, java.util.Map, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0052, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0053, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r8.cancel(r9);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005c, code lost:
        throw r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object mapIndexedNotNullTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel r8, java.util.Collection r9, kotlin.jvm.functions.Function2 r10, kotlin.coroutines.Continuation r11) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r8.iterator()     // Catch:{ all -> 0x0050 }
            r3 = 0
            r4 = 0
        L_0x000a:
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0050 }
            java.lang.Object r5 = r2.hasNext(r11)     // Catch:{ all -> 0x0050 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0050 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0050 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0050 }
            if (r5 == 0) goto L_0x0044
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0050 }
            java.lang.Object r5 = r2.next(r11)     // Catch:{ all -> 0x0050 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0050 }
            kotlin.collections.IndexedValue r6 = new kotlin.collections.IndexedValue     // Catch:{ all -> 0x0050 }
            int r7 = r4 + 1
            r6.<init>(r4, r5)     // Catch:{ all -> 0x0050 }
            int r4 = r6.component1()     // Catch:{ all -> 0x0050 }
            java.lang.Object r5 = r6.component2()     // Catch:{ all -> 0x0050 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0050 }
            java.lang.Object r4 = r10.invoke(r4, r5)     // Catch:{ all -> 0x0050 }
            if (r4 == 0) goto L_0x0042
            r9.add(r4)     // Catch:{ all -> 0x0050 }
        L_0x0042:
            r4 = r7
            goto L_0x000a
        L_0x0044:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0050 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r8.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r9
        L_0x0050:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0052 }
        L_0x0052:
            r10 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r8.cancel(r9)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapIndexedNotNullTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005c, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005d, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r8.cancel(r9);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0066, code lost:
        throw r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object mapIndexedNotNullTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel r8, kotlinx.coroutines.channels.SendChannel r9, kotlin.jvm.functions.Function2 r10, kotlin.coroutines.Continuation r11) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r8.iterator()     // Catch:{ all -> 0x005a }
            r3 = 0
            r4 = 0
        L_0x000a:
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x005a }
            java.lang.Object r5 = r2.hasNext(r11)     // Catch:{ all -> 0x005a }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x005a }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x005a }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x005a }
            if (r5 == 0) goto L_0x004e
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x005a }
            java.lang.Object r5 = r2.next(r11)     // Catch:{ all -> 0x005a }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x005a }
            kotlin.collections.IndexedValue r6 = new kotlin.collections.IndexedValue     // Catch:{ all -> 0x005a }
            int r7 = r4 + 1
            r6.<init>(r4, r5)     // Catch:{ all -> 0x005a }
            int r4 = r6.component1()     // Catch:{ all -> 0x005a }
            java.lang.Object r5 = r6.component2()     // Catch:{ all -> 0x005a }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x005a }
            java.lang.Object r4 = r10.invoke(r4, r5)     // Catch:{ all -> 0x005a }
            if (r4 == 0) goto L_0x004c
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x005a }
            r9.send(r4, r11)     // Catch:{ all -> 0x005a }
            r4 = 2
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x005a }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x005a }
        L_0x004c:
            r4 = r7
            goto L_0x000a
        L_0x004e:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x005a }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r8.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r9
        L_0x005a:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x005c }
        L_0x005c:
            r10 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r8.cancel(r9)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapIndexedNotNullTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0043, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0044, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r7.cancel(r8);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004d, code lost:
        throw r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object mapIndexedTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel r7, java.util.Collection r8, kotlin.jvm.functions.Function2 r9, kotlin.coroutines.Continuation r10) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r7.iterator()     // Catch:{ all -> 0x0041 }
            r3 = 0
            r4 = 0
        L_0x000a:
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0041 }
            java.lang.Object r5 = r2.hasNext(r10)     // Catch:{ all -> 0x0041 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0041 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0041 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0041 }
            if (r5 == 0) goto L_0x0035
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0041 }
            java.lang.Object r5 = r2.next(r10)     // Catch:{ all -> 0x0041 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0041 }
            int r6 = r4 + 1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0041 }
            java.lang.Object r4 = r9.invoke(r4, r5)     // Catch:{ all -> 0x0041 }
            r8.add(r4)     // Catch:{ all -> 0x0041 }
            r4 = r6
            goto L_0x000a
        L_0x0035:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0041 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r7.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r8
        L_0x0041:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0043 }
        L_0x0043:
            r9 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r7.cancel(r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapIndexedTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004d, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004e, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r7.cancel(r8);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0057, code lost:
        throw r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object mapIndexedTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel r7, kotlinx.coroutines.channels.SendChannel r8, kotlin.jvm.functions.Function2 r9, kotlin.coroutines.Continuation r10) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r7.iterator()     // Catch:{ all -> 0x004b }
            r3 = 0
            r4 = 0
        L_0x000a:
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x004b }
            java.lang.Object r5 = r2.hasNext(r10)     // Catch:{ all -> 0x004b }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x004b }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x004b }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x004b }
            if (r5 == 0) goto L_0x003f
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x004b }
            java.lang.Object r5 = r2.next(r10)     // Catch:{ all -> 0x004b }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x004b }
            int r6 = r4 + 1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x004b }
            java.lang.Object r4 = r9.invoke(r4, r5)     // Catch:{ all -> 0x004b }
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x004b }
            r8.send(r4, r10)     // Catch:{ all -> 0x004b }
            r4 = 2
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x004b }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x004b }
            r4 = r6
            goto L_0x000a
        L_0x003f:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x004b }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r7.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r8
        L_0x004b:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x004d }
        L_0x004d:
            r9 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r7.cancel(r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapIndexedTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003d, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003e, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r5.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0047, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object mapNotNullTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel r5, java.util.Collection r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r5.iterator()     // Catch:{ all -> 0x003b }
        L_0x0008:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x003b }
            java.lang.Object r4 = r2.hasNext(r8)     // Catch:{ all -> 0x003b }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x003b }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x003b }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x003b }
            if (r4 == 0) goto L_0x002f
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x003b }
            java.lang.Object r3 = r2.next(r8)     // Catch:{ all -> 0x003b }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x003b }
            java.lang.Object r3 = r7.invoke(r3)     // Catch:{ all -> 0x003b }
            if (r3 == 0) goto L_0x0008
            r6.add(r3)     // Catch:{ all -> 0x003b }
            goto L_0x0008
        L_0x002f:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x003b }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r6
        L_0x003b:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x003d }
        L_0x003d:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapNotNullTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0047, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0048, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r5.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0051, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object mapNotNullTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel r5, kotlinx.coroutines.channels.SendChannel r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r5.iterator()     // Catch:{ all -> 0x0045 }
        L_0x0008:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0045 }
            java.lang.Object r4 = r2.hasNext(r8)     // Catch:{ all -> 0x0045 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0045 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0045 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0045 }
            if (r4 == 0) goto L_0x0039
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0045 }
            java.lang.Object r4 = r2.next(r8)     // Catch:{ all -> 0x0045 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0045 }
            java.lang.Object r4 = r7.invoke(r4)     // Catch:{ all -> 0x0045 }
            if (r4 == 0) goto L_0x0008
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0045 }
            r6.send(r4, r8)     // Catch:{ all -> 0x0045 }
            r3 = 2
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0045 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0045 }
            goto L_0x0008
        L_0x0039:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0045 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r6
        L_0x0045:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapNotNullTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003b, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003c, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r5.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0045, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object mapTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel r5, java.util.Collection r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r5.iterator()     // Catch:{ all -> 0x0039 }
        L_0x0008:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0039 }
            java.lang.Object r4 = r2.hasNext(r8)     // Catch:{ all -> 0x0039 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0039 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0039 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0039 }
            if (r4 == 0) goto L_0x002d
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0039 }
            java.lang.Object r3 = r2.next(r8)     // Catch:{ all -> 0x0039 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0039 }
            java.lang.Object r3 = r7.invoke(r3)     // Catch:{ all -> 0x0039 }
            r6.add(r3)     // Catch:{ all -> 0x0039 }
            goto L_0x0008
        L_0x002d:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0039 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r6
        L_0x0039:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x003b }
        L_0x003b:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel, java.util.Collection, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0045, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r5.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004f, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object mapTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel r5, kotlinx.coroutines.channels.SendChannel r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r5.iterator()     // Catch:{ all -> 0x0043 }
        L_0x0008:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0043 }
            java.lang.Object r4 = r2.hasNext(r8)     // Catch:{ all -> 0x0043 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0043 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0043 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0043 }
            if (r4 == 0) goto L_0x0037
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0043 }
            java.lang.Object r4 = r2.next(r8)     // Catch:{ all -> 0x0043 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0043 }
            java.lang.Object r4 = r7.invoke(r4)     // Catch:{ all -> 0x0043 }
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0043 }
            r6.send(r4, r8)     // Catch:{ all -> 0x0043 }
            r3 = 2
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0043 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0043 }
            goto L_0x0008
        L_0x0037:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0043 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r6
        L_0x0043:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.mapTo$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlinx.coroutines.channels.SendChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0052, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0053, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r5.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005c, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object all$$forInline(kotlinx.coroutines.channels.ReceiveChannel r5, kotlin.jvm.functions.Function1 r6, kotlin.coroutines.Continuation r7) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r5.iterator()     // Catch:{ all -> 0x0050 }
        L_0x0008:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0050 }
            java.lang.Object r4 = r2.hasNext(r7)     // Catch:{ all -> 0x0050 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0050 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0050 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0050 }
            if (r4 == 0) goto L_0x0040
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0050 }
            java.lang.Object r4 = r2.next(r7)     // Catch:{ all -> 0x0050 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0050 }
            java.lang.Object r4 = r6.invoke(r4)     // Catch:{ all -> 0x0050 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0050 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0050 }
            if (r4 != 0) goto L_0x0008
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r3)     // Catch:{ all -> 0x0050 }
            r7 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r7)
            r5.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r7)
            return r6
        L_0x0040:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0050 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)
            return r5
        L_0x0050:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0052 }
        L_0x0052:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.all$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0052, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0053, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r5.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005c, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object any$$forInline(kotlinx.coroutines.channels.ReceiveChannel r5, kotlin.jvm.functions.Function1 r6, kotlin.coroutines.Continuation r7) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r5.iterator()     // Catch:{ all -> 0x0050 }
        L_0x0008:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0050 }
            java.lang.Object r4 = r2.hasNext(r7)     // Catch:{ all -> 0x0050 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0050 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0050 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0050 }
            if (r4 == 0) goto L_0x0040
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0050 }
            java.lang.Object r3 = r2.next(r7)     // Catch:{ all -> 0x0050 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0050 }
            java.lang.Object r3 = r6.invoke(r3)     // Catch:{ all -> 0x0050 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x0050 }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x0050 }
            if (r3 == 0) goto L_0x0008
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x0050 }
            r7 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r7)
            r5.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r7)
            return r6
        L_0x0040:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0050 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r3)
            return r5
        L_0x0050:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0052 }
        L_0x0052:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.any$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0047, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0048, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r6.cancel(r7);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0051, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object count$$forInline(kotlinx.coroutines.channels.ReceiveChannel r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r6.iterator()     // Catch:{ all -> 0x0045 }
            r3 = 0
            r4 = 0
        L_0x000a:
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0045 }
            java.lang.Object r5 = r2.hasNext(r8)     // Catch:{ all -> 0x0045 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0045 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0045 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0045 }
            if (r5 == 0) goto L_0x0035
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0045 }
            java.lang.Object r5 = r2.next(r8)     // Catch:{ all -> 0x0045 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0045 }
            java.lang.Object r5 = r7.invoke(r5)     // Catch:{ all -> 0x0045 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0045 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0045 }
            if (r5 == 0) goto L_0x000a
            int r4 = r4 + 1
            goto L_0x000a
        L_0x0035:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0045 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r6.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r4)
            return r6
        L_0x0045:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r6.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.count$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0038, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0039, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r5.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0042, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object fold$$forInline(kotlinx.coroutines.channels.ReceiveChannel r5, java.lang.Object r6, kotlin.jvm.functions.Function2 r7, kotlin.coroutines.Continuation r8) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r5.iterator()     // Catch:{ all -> 0x0036 }
        L_0x0008:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0036 }
            java.lang.Object r4 = r2.hasNext(r8)     // Catch:{ all -> 0x0036 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0036 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0036 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0036 }
            if (r4 == 0) goto L_0x002a
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0036 }
            java.lang.Object r3 = r2.next(r8)     // Catch:{ all -> 0x0036 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0036 }
            java.lang.Object r6 = r7.invoke(r6, r3)     // Catch:{ all -> 0x0036 }
            goto L_0x0008
        L_0x002a:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0036 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r6
        L_0x0036:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0038 }
        L_0x0038:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.fold$$forInline(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0040, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0041, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r7.cancel(r8);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004a, code lost:
        throw r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object foldIndexed$$forInline(kotlinx.coroutines.channels.ReceiveChannel r7, java.lang.Object r8, kotlin.jvm.functions.Function3 r9, kotlin.coroutines.Continuation r10) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r7.iterator()     // Catch:{ all -> 0x003e }
            r3 = 0
            r4 = 0
        L_0x000a:
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x003e }
            java.lang.Object r5 = r2.hasNext(r10)     // Catch:{ all -> 0x003e }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x003e }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x003e }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x003e }
            if (r5 == 0) goto L_0x0032
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x003e }
            java.lang.Object r5 = r2.next(r10)     // Catch:{ all -> 0x003e }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x003e }
            int r6 = r4 + 1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x003e }
            java.lang.Object r8 = r9.invoke(r4, r8, r5)     // Catch:{ all -> 0x003e }
            r4 = r6
            goto L_0x000a
        L_0x0032:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x003e }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r7.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r8
        L_0x003e:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0040 }
        L_0x0040:
            r9 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r7.cancel(r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.foldIndexed$$forInline(kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0069, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006a, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r9.cancel(r10);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0073, code lost:
        throw r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object maxBy$$forInline(kotlinx.coroutines.channels.ReceiveChannel r9, kotlin.jvm.functions.Function1 r10, kotlin.coroutines.Continuation r11) {
        /*
            r0 = 0
            r1 = r0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = 1
            kotlinx.coroutines.channels.ChannelIterator r3 = r9.iterator()     // Catch:{ all -> 0x0067 }
            r4 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0067 }
            java.lang.Object r5 = r3.hasNext(r11)     // Catch:{ all -> 0x0067 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0067 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0067 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0067 }
            if (r5 != 0) goto L_0x0027
            r10 = 3
            kotlin.jvm.internal.InlineMarker.finallyStart(r10)
        L_0x0020:
            r9.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r10)
            return r0
        L_0x0027:
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0067 }
            java.lang.Object r0 = r3.next(r11)     // Catch:{ all -> 0x0067 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0067 }
            java.lang.Object r5 = r10.invoke(r0)     // Catch:{ all -> 0x0067 }
            java.lang.Comparable r5 = (java.lang.Comparable) r5     // Catch:{ all -> 0x0067 }
        L_0x0037:
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0067 }
            java.lang.Object r6 = r3.hasNext(r11)     // Catch:{ all -> 0x0067 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0067 }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x0067 }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x0067 }
            if (r6 == 0) goto L_0x0062
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0067 }
            java.lang.Object r6 = r3.next(r11)     // Catch:{ all -> 0x0067 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0067 }
            java.lang.Object r7 = r10.invoke(r6)     // Catch:{ all -> 0x0067 }
            java.lang.Comparable r7 = (java.lang.Comparable) r7     // Catch:{ all -> 0x0067 }
            int r8 = r5.compareTo(r7)     // Catch:{ all -> 0x0067 }
            if (r8 >= 0) goto L_0x0037
            r0 = r6
            r5 = r7
            goto L_0x0037
        L_0x0062:
            r10 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r10)
            goto L_0x0020
        L_0x0067:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x0069 }
        L_0x0069:
            r11 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r9.cancel(r10)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.maxBy$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0069, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006a, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r9.cancel(r10);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0073, code lost:
        throw r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object minBy$$forInline(kotlinx.coroutines.channels.ReceiveChannel r9, kotlin.jvm.functions.Function1 r10, kotlin.coroutines.Continuation r11) {
        /*
            r0 = 0
            r1 = r0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = 1
            kotlinx.coroutines.channels.ChannelIterator r3 = r9.iterator()     // Catch:{ all -> 0x0067 }
            r4 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0067 }
            java.lang.Object r5 = r3.hasNext(r11)     // Catch:{ all -> 0x0067 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0067 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0067 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0067 }
            if (r5 != 0) goto L_0x0027
            r10 = 3
            kotlin.jvm.internal.InlineMarker.finallyStart(r10)
        L_0x0020:
            r9.cancel(r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r10)
            return r0
        L_0x0027:
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0067 }
            java.lang.Object r0 = r3.next(r11)     // Catch:{ all -> 0x0067 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0067 }
            java.lang.Object r5 = r10.invoke(r0)     // Catch:{ all -> 0x0067 }
            java.lang.Comparable r5 = (java.lang.Comparable) r5     // Catch:{ all -> 0x0067 }
        L_0x0037:
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0067 }
            java.lang.Object r6 = r3.hasNext(r11)     // Catch:{ all -> 0x0067 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0067 }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x0067 }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x0067 }
            if (r6 == 0) goto L_0x0062
            kotlin.jvm.internal.InlineMarker.mark((int) r4)     // Catch:{ all -> 0x0067 }
            java.lang.Object r6 = r3.next(r11)     // Catch:{ all -> 0x0067 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x0067 }
            java.lang.Object r7 = r10.invoke(r6)     // Catch:{ all -> 0x0067 }
            java.lang.Comparable r7 = (java.lang.Comparable) r7     // Catch:{ all -> 0x0067 }
            int r8 = r5.compareTo(r7)     // Catch:{ all -> 0x0067 }
            if (r8 <= 0) goto L_0x0037
            r0 = r6
            r5 = r7
            goto L_0x0037
        L_0x0062:
            r10 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r10)
            goto L_0x0020
        L_0x0067:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x0069 }
        L_0x0069:
            r11 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r2)
            r9.cancel(r10)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r2)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.minBy$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0052, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0053, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r5.cancel(r6);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005c, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object none$$forInline(kotlinx.coroutines.channels.ReceiveChannel r5, kotlin.jvm.functions.Function1 r6, kotlin.coroutines.Continuation r7) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r5.iterator()     // Catch:{ all -> 0x0050 }
        L_0x0008:
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0050 }
            java.lang.Object r4 = r2.hasNext(r7)     // Catch:{ all -> 0x0050 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0050 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0050 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0050 }
            if (r4 == 0) goto L_0x0040
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0050 }
            java.lang.Object r4 = r2.next(r7)     // Catch:{ all -> 0x0050 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0050 }
            java.lang.Object r4 = r6.invoke(r4)     // Catch:{ all -> 0x0050 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0050 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0050 }
            if (r4 == 0) goto L_0x0008
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r3)     // Catch:{ all -> 0x0050 }
            r7 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r7)
            r5.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r7)
            return r6
        L_0x0040:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0050 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)
            return r5
        L_0x0050:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0052 }
        L_0x0052:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r5.cancel(r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.none$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005d, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005e, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r6.cancel(r7);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0067, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object reduce$$forInline(kotlinx.coroutines.channels.ReceiveChannel r6, kotlin.jvm.functions.Function2 r7, kotlin.coroutines.Continuation r8) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r6.iterator()     // Catch:{ all -> 0x005b }
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x005b }
            java.lang.Object r4 = r2.hasNext(r8)     // Catch:{ all -> 0x005b }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x005b }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x005b }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x005b }
            if (r4 == 0) goto L_0x0051
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x005b }
            java.lang.Object r4 = r2.next(r8)     // Catch:{ all -> 0x005b }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x005b }
        L_0x0025:
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x005b }
            java.lang.Object r5 = r2.hasNext(r8)     // Catch:{ all -> 0x005b }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x005b }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x005b }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x005b }
            if (r5 == 0) goto L_0x0046
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x005b }
            java.lang.Object r5 = r2.next(r8)     // Catch:{ all -> 0x005b }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x005b }
            java.lang.Object r4 = r7.invoke(r4, r5)     // Catch:{ all -> 0x005b }
            goto L_0x0025
        L_0x0046:
            r7 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r7)
            r6.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r7)
            return r4
        L_0x0051:
            java.lang.UnsupportedOperationException r7 = new java.lang.UnsupportedOperationException     // Catch:{ all -> 0x005b }
            java.lang.String r8 = "Empty channel can't be reduced."
            r7.<init>(r8)     // Catch:{ all -> 0x005b }
            java.lang.Throwable r7 = (java.lang.Throwable) r7     // Catch:{ all -> 0x005b }
            throw r7     // Catch:{ all -> 0x005b }
        L_0x005b:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x005d }
        L_0x005d:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r6.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.reduce$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0064, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0065, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r8.cancel(r9);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006e, code lost:
        throw r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object reduceIndexed$$forInline(kotlinx.coroutines.channels.ReceiveChannel r8, kotlin.jvm.functions.Function3 r9, kotlin.coroutines.Continuation r10) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r8.iterator()     // Catch:{ all -> 0x0062 }
            r3 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0062 }
            java.lang.Object r4 = r2.hasNext(r10)     // Catch:{ all -> 0x0062 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0062 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0062 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0062 }
            if (r4 == 0) goto L_0x0058
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0062 }
            java.lang.Object r4 = r2.next(r10)     // Catch:{ all -> 0x0062 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0062 }
            r5 = 1
        L_0x0026:
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0062 }
            java.lang.Object r6 = r2.hasNext(r10)     // Catch:{ all -> 0x0062 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0062 }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x0062 }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x0062 }
            if (r6 == 0) goto L_0x004d
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0062 }
            int r5 = r5 + 1
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0062 }
            java.lang.Object r7 = r2.next(r10)     // Catch:{ all -> 0x0062 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0062 }
            java.lang.Object r4 = r9.invoke(r6, r4, r7)     // Catch:{ all -> 0x0062 }
            goto L_0x0026
        L_0x004d:
            r9 = 2
            kotlin.jvm.internal.InlineMarker.finallyStart(r9)
            r8.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r9)
            return r4
        L_0x0058:
            java.lang.UnsupportedOperationException r9 = new java.lang.UnsupportedOperationException     // Catch:{ all -> 0x0062 }
            java.lang.String r10 = "Empty channel can't be reduced."
            r9.<init>(r10)     // Catch:{ all -> 0x0062 }
            java.lang.Throwable r9 = (java.lang.Throwable) r9     // Catch:{ all -> 0x0062 }
            throw r9     // Catch:{ all -> 0x0062 }
        L_0x0062:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0064 }
        L_0x0064:
            r10 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r8.cancel(r9)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.reduceIndexed$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0044, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0045, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r6.cancel(r7);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004e, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object sumBy$$forInline(kotlinx.coroutines.channels.ReceiveChannel r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r6.iterator()     // Catch:{ all -> 0x0042 }
            r3 = 0
            r4 = 0
        L_0x000a:
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0042 }
            java.lang.Object r5 = r2.hasNext(r8)     // Catch:{ all -> 0x0042 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0042 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0042 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0042 }
            if (r5 == 0) goto L_0x0032
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x0042 }
            java.lang.Object r5 = r2.next(r8)     // Catch:{ all -> 0x0042 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0042 }
            java.lang.Object r5 = r7.invoke(r5)     // Catch:{ all -> 0x0042 }
            java.lang.Number r5 = (java.lang.Number) r5     // Catch:{ all -> 0x0042 }
            int r5 = r5.intValue()     // Catch:{ all -> 0x0042 }
            int r4 = r4 + r5
            goto L_0x000a
        L_0x0032:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0042 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r6.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r4)
            return r6
        L_0x0042:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r6.cancel(r7)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.sumBy$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0045, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        r7.cancel(r8);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004f, code lost:
        throw r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object sumByDouble$$forInline(kotlinx.coroutines.channels.ReceiveChannel r7, kotlin.jvm.functions.Function1 r8, kotlin.coroutines.Continuation r9) {
        /*
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            kotlinx.coroutines.channels.ChannelIterator r2 = r7.iterator()     // Catch:{ all -> 0x0043 }
            r3 = 0
        L_0x000a:
            r5 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r5)     // Catch:{ all -> 0x0043 }
            java.lang.Object r6 = r2.hasNext(r9)     // Catch:{ all -> 0x0043 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0043 }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x0043 }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x0043 }
            if (r6 == 0) goto L_0x0033
            kotlin.jvm.internal.InlineMarker.mark((int) r5)     // Catch:{ all -> 0x0043 }
            java.lang.Object r5 = r2.next(r9)     // Catch:{ all -> 0x0043 }
            kotlin.jvm.internal.InlineMarker.mark((int) r1)     // Catch:{ all -> 0x0043 }
            java.lang.Object r5 = r8.invoke(r5)     // Catch:{ all -> 0x0043 }
            java.lang.Number r5 = (java.lang.Number) r5     // Catch:{ all -> 0x0043 }
            double r5 = r5.doubleValue()     // Catch:{ all -> 0x0043 }
            double r3 = r3 + r5
            goto L_0x000a
        L_0x0033:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0043 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r7.cancel(r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            java.lang.Double r7 = java.lang.Double.valueOf(r3)
            return r7
        L_0x0043:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r9 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r7.cancel(r8)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.sumByDouble$$forInline(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
