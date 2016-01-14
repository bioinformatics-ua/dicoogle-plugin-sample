module.exports = {
  entry: './src/index.js',
  output: {
      filename: 'module.js',
      libraryTarget: 'commonjs2'
  },
  module: {
    loaders: [
      {
        test: /src\/.*\.js?$/,
        exclude: /node_modules/,
        loader: 'babel', 
        query: {
          presets: ['es2015']
        }
      }
    ]
  },
  externals: ['react', 'dicoogle-client', 'dicoole-webcore']
};
