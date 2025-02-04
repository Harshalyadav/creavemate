const webpack = require('webpack');

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
        configure: (webpackConfig) => {
          webpackConfig.resolve.fallback = {
            ...webpackConfig.resolve.fallback,
       
            crypto: require.resolve("crypto-browserify"),
            stream: require.resolve("stream-browserify"),
          };
          webpackConfig.plugins.push(
            new webpack.ProvidePlugin({
              process: "process/browser",
            })
          );
          return webpackConfig;
        },
      },
  };
