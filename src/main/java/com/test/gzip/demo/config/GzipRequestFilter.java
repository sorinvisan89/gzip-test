package com.test.gzip.demo.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class GzipRequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        String contentEncoding = httpRequest.getHeader("Content-Encoding");

        if (!"gzip".equalsIgnoreCase(contentEncoding)) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Request must be gzip-encoded");
            return; 
        }

        request = new HttpServletRequestWrapper(httpRequest) {
            @Override
            public ServletInputStream getInputStream() throws IOException {
                return new GZIPServletInputStream(httpRequest.getInputStream());
            }
        };

        chain.doFilter(request, response);
    }

    static class GZIPServletInputStream extends ServletInputStream {
        private final GZIPInputStream gzipStream;

        public GZIPServletInputStream(InputStream input) throws IOException {
            this.gzipStream = new GZIPInputStream(input);
        }

        @Override
        public int read() throws IOException {
            return gzipStream.read();
        }

        @Override
        public boolean isFinished() { return false; }

        @Override
        public boolean isReady() { return true; }

        @Override
        public void setReadListener(ReadListener listener) { }
    }

}