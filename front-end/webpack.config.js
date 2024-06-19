const path = require('path');
const MinifyPlugin = require('babel-minify-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const BrowserSyncPlugin = require('browser-sync-webpack-plugin');

module.exports = {
    mode:'development',
    devtool: 'source-map',
    context: path.resolve(__dirname, 'source'),
    entry: ['./main.js', './main.scss', './login.scss'],
    output: {
         path: path.resolve(__dirname, 'assets')
    },
    module: {
        rules:[
            {
                test: /.(js|jsx)$/,
                exclude: /node_modules/,
                loader: 'babel-loader'
            },
            {
                test: /.(scss|css)$/,
                exclude: /node_modules/,
                use:[
                    {
                        loader: MiniCssExtractPlugin.loader,
                        options: {
                           //comentario
                        }
                    },
                    'css-loader',
                    'postcss-loader',
                    'sass-loader'
                ]

            },
            {
                test: /\.(svg|eot|woff|woff2|ttf)$/,
                loader: 'file-loader',
                options: {
                    outputPath: 'fonts'
                }
            }

         ]
    },
    plugins: [
        new MinifyPlugin({}, {
            comments: false
        }),

        new MiniCssExtractPlugin({},{
            filename: '[name].css'
        }),

        new BrowserSyncPlugin({
            host: 'localhost',
            port: 3000,
            proxy: 'http://localhost:4000',
            files: ['./../src/main/resources/templates/']

        })

    ]


}