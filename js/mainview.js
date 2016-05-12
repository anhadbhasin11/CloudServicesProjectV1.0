/**
 * Created by vidyakhadsare on 5/8/16.
 */
var React = require('react');
var ReactDOM = require('react-dom');
var _ = require('underscore');
var $ = require('jquery');

var Products = require('./model/ProductCollection');
var Customers = require('./model/CustomerCollection');
var Orders = require('./model/OrderCollection');

var TableView = require('./table/tableview');

var MainView = React.createClass({

  products: null,
  customers: null,
  orders: null,

  getInitialState: function () {
    this.customers = new Customers();
    this.products = new Products();
    this.orders = new Orders();

    return {
      products: [],
      customers: [],
      orders: []
    };

  },

  componentDidMount: function () {
    var customerDeferred = this.customers.fetch();
    var productDeferred = this.products.fetch();
    var orderDeferred = this.orders.fetch();

    $.when(customerDeferred, productDeferred, orderDeferred).then(this.onSuccess, this.onError);
  },

  onSuccess: function () {
    console.log('on success');
    this._setState({
      products: this.products.getData()
    });
  },

  onError: function () {
    console.log('Error when fetching data.');
  },

  render: function () {
    return (
        <div className="main-view" ref="mainView">
          <TableView dataType="product" onRowClick={ this.onRowClick } columns={ this.products.getProperties() } data={ this.state.products } />
          <TableView dataType="order" onRowClick={ this.onRowClick } columns={ this.orders.getProperties() } data={ this.state.orders } />
          <TableView dataType="customer" onRowClick={ this.onRowClick } columns={ this.customers.getProperties() } data={ this.state.customers } />
        </div>
    );
  },

  onRowClick: function (dataType, evt) {
    var nativeEvt = evt.nativeEvent;

    var rows = $(this.refs.mainView).find('.row');
    rows.removeClass('active');

    var parentRow = $(nativeEvt.target).parents('.row');
    parentRow.addClass('active');
    var key = parentRow.attr('data-key');

    switch (dataType) {
      case 'product':
        this._setState({
          orders: this.orders.getData({
            productCode: key
          })
        });
        break;
      case 'order':
        this._setState({
          customers: this.customers.getData({
            customerNumber: Number(key)
          })
        });
        break;
    }
  },

  _setState: function (newData) {
    var currentState = this.state,
        newState;

    newState = _.extend({}, currentState, newData);
    this.setState(newState);
  }


});

module.exports = MainView;
