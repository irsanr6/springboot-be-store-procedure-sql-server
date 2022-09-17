package com.irsan.springbootsprestapi.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
public class CompressionUtil {

    public static String compressB64(String text) throws IOException {
        return new String(Base64.getEncoder().encode(compress(text)));
    }

    public static String decompressB64(String b64Compressed) throws IOException {
        byte[] decompressedBArray = decompress(Base64.getDecoder().decode(b64Compressed));
        return new String(decompressedBArray, StandardCharsets.UTF_8);
    }

    public static byte[] compress(String text) throws IOException {
        return compress(text.getBytes());
    }

    public static byte[] compress(byte[] bArray) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try (DeflaterOutputStream dos = new DeflaterOutputStream(os)) {
            dos.write(bArray);
        }
        return os.toByteArray();
    }

    public static byte[] decompress(byte[] compressedTxt) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try (OutputStream ios = new InflaterOutputStream(os)) {
            ios.write(compressedTxt);
        }

        return os.toByteArray();
    }

}
