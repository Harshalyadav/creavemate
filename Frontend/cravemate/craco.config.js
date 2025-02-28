module.exports = {
  style: {
    postcss: {
      plugins: [
        require("tailwindcss"),
        require("autoprefixer"),
      ],
    },
  },
  webpack: {
    configure: {
      resolve: {
        fallback: {
          crypto: require.resolve("crypto-browserify"),
          stream: require.resolve("stream-browserify"),
          buffer: require.resolve("buffer"),
          vm: require.resolve("vm-browserify"), 
        },
      },
    },
    plugins: [
      new (require("webpack").ProvidePlugin)({
        Buffer: ["buffer", "Buffer"],
      }),
    ],
  },
};
