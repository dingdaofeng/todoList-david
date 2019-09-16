'use strict';

import { apiClient } from '../service/apiclient';
import { createStore, combineReducers, applyMiddleware } from 'redux';
import { routerReducer } from 'react-router-redux';

import reducers from './reducers';

import { ADD_TODO, COMPLITE_TODO, DELETE_TODO } from '../constants/ActionTypes';
import { SHOW_TODO } from '../constants/FilterTypes';

let updateStorage = store => next => action => {
    switch (action.type) {
        case ADD_TODO:
            apiClient.post('add', action.payload)
              .then((response)=>{
            })
            break;
        case COMPLITE_TODO:
            apiClient.post('complete/' + action.index)
              .then((response)=>{
            })
            break;
        case DELETE_TODO:
            apiClient.post('delete/' + action.index)
              .then((response)=>{
            })
            break;
    }
    let result = next(action);

    return result;
};

let wrapStoreWithMiddleware = applyMiddleware(updateStorage)(createStore);
let store = wrapStoreWithMiddleware(combineReducers({
    list: reducers.list,
    filter: reducers.filter,
    routing: routerReducer
})
// , {
//     list: storage.get(),
//     filter: SHOW_TODO,
//     routing: ''
// }
);



export default store;
