/**
 * Created by vidyakhadsare on 5/8/16.
 */
var Backbone = require('backbone');
var Constants = require('../utils/Constants');

var _ = require('underscore');

var DP = {
  NUM: 'orderNumber',
  DATE: 'orderDate',
  REQ_DATE: 'requiredDate',
  SHIP_DATE: 'shippedDate',
  CONT_NUM: 'contactNumber',
  STATUS: 'status',
  CUST_NUM: 'customerNumber'
};

var OrderModel = Backbone.Model.extend({
  DP: DP,

  getDataColumns: function () {
    return [
      { key: false, value: this.get(DP.NUM)},
      { key: false, value: this.get(DP.DATE)},
      { key: false, value: this.get(DP.REQ_DATE)},
      { key: false, value: this.get(DP.SHIP_DATE)},
      { key: false, value: this.get(DP.CONT_NUM)},
      { key: false, value: this.get(DP.STATUS)},
      { key: true, value: this.get(DP.CUST_NUM)}
    ];
  }
});

var OrderCollection = Backbone.Collection.extend({
  model: OrderModel,

  url: Constants.URLS.ORDERS,

  getProperties: function () {
    return [
      'Order Number',
      'Date',
      'Require Date',
      'Shipped Date',
      'Contact No',
      'Status',
      'Customer Number'
    ];
  },

  getData: function (filter) {
    var models = this.models;
    if (filter) {
      models = this.where(filter);
    }

    var data = [];

    _.each(models, function (model) {
      data.push(model.getDataColumns());
    });
    return data;
  }
});

module.exports = OrderCollection;