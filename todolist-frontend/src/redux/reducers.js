'use strict';

import { ADD_TODO, COMPLITE_TODO, FRESH_TODO, DELETE_TODO, SET_FILTER } from '../constants/ActionTypes';
import { SHOW_DONE, SHOW_TODO } from '../constants/FilterTypes';

function list(state = [], action) {
    switch (action.type) {
        case FRESH_TODO:
            return action.payload;
        case ADD_TODO:
            alert(state);
            return [
                ...state,
                Object.assign({}, action.payload, {
                    done: false
                })
            ];
        case COMPLITE_TODO:
            return [...state].map(item => {
                item.id === action.index && (item.done = true);
                return item;
            });
        case DELETE_TODO:
            return [...state].filter(item => item.id !== action.index);
        default:
            return state;
    }
}

function visiableFilter(state = SHOW_TODO, action) {
    switch (action.type) {
        case SET_FILTER:
            return action.filter
                ? SHOW_TODO
                : SHOW_DONE;
        default:
            return state;
    }
}

export default {
    list,
    filter: visiableFilter
};
