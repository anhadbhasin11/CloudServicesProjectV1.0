/**
 * Created by vidyakhadsare on 5/8/16.
 */
var Backbone = require('backbone');
var Constants = require('../utils/Constants');

var _ = require('underscore');

var DP = {
  CODE: "productCode",
  NAME: "productName",
  SCALE: "productScale",
  VENDOR: "productVendor",
  QTY_IN_STK: "quantityInStock",
  MSRP: "MSRP"
};

var ProductModel = Backbone.Model.extend({
  DP: DP,

  getDataColumns: function () {
    return [
      { key: true, value: this.get(DP.CODE)},
      { key: false, value: this.get(DP.NAME)},
      { key: false, value: this.get(DP.SCALE)},
      { key: false, value: this.get(DP.VENDOR)},
      { key: false, value: this.get(DP.QTY_IN_STK)},
      { key: false, value: this.get(DP.MSRP)}
    ];
  }

});

var ProductCollection = Backbone.Collection.extend({
  model: ProductModel,

  url: Constants.URLS.PRODUCTS,
  
  getProperties: function () {
    return [
      'Product Code',
      'Name',
      'Scale',
      'Vendor',
      'Qty',
      'MSRP'
    ];
  },

  getData: function (filter) {
    var models = this.models;
    if (filter) {
      models = this.where(filter);
    }

    var data = [];

    _.each(this.models, function (model) {
      data.push(model.getDataColumns());
    });
    return data;
  }
});

module.exports = ProductCollection;