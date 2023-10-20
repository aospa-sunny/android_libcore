/*
 * Copyright (c) 2000, 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

// -- This file was mechanically generated: Do not edit! -- //
// Android-note: This file is generated by ojluni/src/tools/gensrc_android.sh.

package java.nio;

import java.util.Objects;

import libcore.io.Memory;

class ByteBufferAsFloatBuffer                  // package-private
    extends FloatBuffer
{



    protected final ByteBuffer bb;


    // Android-added: Added offset as address can be zero on Android.
    protected final int offset;
    // Android-added: Merge with little- and big-endian classes.
    private final ByteOrder order;

    // Android-changed: Added ByteOrder and removed MemorySegmentProxy to be supported yet.
    ByteBufferAsFloatBuffer(ByteBuffer bb,
                                     int mark, int pos, int lim, int cap,
                                     int off, ByteOrder order)
    {

        // Android-removed: Android duplicates the buffer, and merges with the read-only buffer.
        // super(mark, pos, lim, cap, segment);
        // this.bb = bb;
        // address = addr;
        // assert address >= bb.address;
        super(mark, pos, lim, cap);
        this.bb = bb.duplicate();
        this.isReadOnly = bb.isReadOnly;
        // There are only two possibilities for the type of ByteBuffer "bb", viz, DirectByteBuffer and
        // HeapByteBuffer. We only have to initialize the field when bb is an instance of
        // DirectByteBuffer.
        // The address field is used by NIOAccess#getBasePointer and GetDirectBufferAddress method
        // in art which return the address of the first usable byte of the underlying memory, i.e,
        // the position of parent buffer. Therefore, value of "off" will be equal to parent buffer's
        // position when the method is called from either HeapByteBuffer or DirectByteBuffer.
        if (bb instanceof DirectByteBuffer) {
            this.address = bb.address + off;
        }
        this.bb.order(order);
        this.order = order;
        offset = off;



    }

    @Override
    Object base() {
        // Android-changed: DirectByteBuffer allocated directly assigns both hb and address field.
        // return bb.hb;
        return bb.base();
    }

    public FloatBuffer slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        // Android-changed: Added ByteOrder and removed MemorySegmentProxy to be supported yet.
        // long addr = byteOffset(pos);
        // return new ByteBufferAsFloatBuffer(bb, -1, 0, rem, rem, addr, order);
        int off = (pos << 2) + offset;
        return new ByteBufferAsFloatBuffer(bb, -1, 0, rem, rem, off, order);
    }

    @Override
    public FloatBuffer slice(int index, int length) {
        Objects.checkFromIndexSize(index, length, limit());
        return new ByteBufferAsFloatBuffer(bb,
                                                    -1,
                                                    0,
                                                    length,
                                                    length,
        // Android-changed: Added ByteOrder and removed MemorySegmentProxy to be supported yet.
                                                    offset, order);
    }

    public FloatBuffer duplicate() {
        return new ByteBufferAsFloatBuffer(bb,
                                                    this.markValue(),
                                                    this.position(),
                                                    this.limit(),
                                                    this.capacity(),
        // Android-changed: Added ByteOrder and removed MemorySegmentProxy to be supported yet.
                                                    offset, order);
    }

    public FloatBuffer asReadOnlyBuffer() {

        return new ByteBufferAsFloatBuffer(bb.asReadOnlyBuffer(),
                                                 this.markValue(),
                                                 this.position(),
                                                 this.limit(),
                                                 this.capacity(),
        // Android-changed: Added ByteOrder and removed MemorySegmentProxy to be supported yet.
                                                 offset, order);



    }



    private int ix(int i) {
        // Android-changed: address can be zero on Android.
        // int off = (int) (address - bb.address);
        // return (i << 2) + off;
        return (i << 2) + offset;
    }

    // Android-removed: Removed unused byteOffset(long).
    /*
    protected long byteOffset(long i) {
        return (i << 2) + address;
    }
    */

    public float get() {
        // Android-changed: Removed MemorySegmentProxy to be supported yet.
        // int x = SCOPED_MEMORY_ACCESS.getIntUnaligned(scope(), bb.hb, byteOffset(nextGetIndex()),
        //     false);
        // return Float.intBitsToFloat(x);
        return get(nextGetIndex());
    }

    public float get(int i) {
        // Android-changed: Removed MemorySegmentProxy to be supported yet.
        // int x = SCOPED_MEMORY_ACCESS.getIntUnaligned(scope(), bb.hb, byteOffset(checkIndex(i)),
        //    false);
        // return Float.intBitsToFloat(x);
        return bb.getFloatUnchecked(ix(checkIndex(i)));
    }

    // BEGIN Android-added: Improve the efficiency of put(type$[], int, int).
    @Override
    public FloatBuffer get(float[] dst, int offset, int length) {
        checkBounds(offset, length, dst.length);
        if (length > remaining())
            throw new BufferUnderflowException();
        bb.getUnchecked(ix(position), dst, offset, length);
        position += length;
        return this;
    }
    // END Android-added: Improve the efficiency of put(type$[], int, int).













    public FloatBuffer put(float x) {

        // Android-changed: Removed MemorySegmentProxy to be supported yet.
        // int y = Float.floatToRawIntBits(x);
        // SCOPED_MEMORY_ACCESS.putIntUnaligned(scope(), bb.hb, byteOffset(nextPutIndex()), y,
        //     false);
        put(nextPutIndex(), x);
        return this;



    }

    public FloatBuffer put(int i, float x) {

        // Android-added: Merge the Read-only buffer class with this Read-Write buffer class.
        throwIfReadOnly();
        // Android-changed: Removed MemorySegmentProxy to be supported yet.
        // int y = Float.floatToRawIntBits(x);
        // SCOPED_MEMORY_ACCESS.putIntUnaligned(scope(), bb.hb, byteOffset(checkIndex(i)), y,
        //     false);
        bb.putFloatUnchecked(ix(checkIndex(i)), x);
        return this;



    }

    // BEGIN Android-added: Improve the efficiency of put(type$[], int, int).
    @Override
    public FloatBuffer put(float[] src, int offset, int length) {
        checkBounds(offset, length, src.length);
        if (length > remaining())
            throw new BufferOverflowException();
        bb.putUnchecked(ix(position), src, offset, length);
        position += length;
        return this;
    }
    // END Android-added: Improve the efficiency of put(type$[], int, int).

    public FloatBuffer compact() {

        // Android-added: Merge the Read-only buffer class with this Read-Write buffer class.
        throwIfReadOnly();
        int pos = position();
        int lim = limit();
        int rem = (pos <= lim ? lim - pos : 0);
        // Android-changed: Improve the efficiency.
        /*
        ByteBuffer db = bb.duplicate();
        db.limit(ix(lim));
        db.position(ix(0));
        ByteBuffer sb = db.slice();
        sb.position(pos << 2);
        sb.compact();
        */
        if (!(bb instanceof DirectByteBuffer)) {
            System.arraycopy(bb.array(), ix(pos), bb.array(), ix(0), rem << 2);
        } else {
            Memory.memmove(this, ix(0), this, ix(pos), rem << 2);
        }
        position(rem);
        limit(capacity());
        discardMark();
        return this;



    }

    public boolean isDirect() {
        return bb.isDirect();
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }









































    public ByteOrder order() {
        return order;
    }







    // Android-added: Merge the Read-only buffer class with this Read-Write buffer class.
    private void throwIfReadOnly() {
        if (isReadOnly) {
            throw new ReadOnlyBufferException();
        }
    }
}
