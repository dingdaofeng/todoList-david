'use strict';

import { ADD_TODO, COMPLITE_TODO,FRESH_TODO, DELETE_TODO, SET_FILTER, UPDATE_TODO, GET_TODO } from '../constants/ActionTypes';

export function addTodo({name, description, dueDate}) {
    return {
        type: ADD_TODO,
        payload: {
            name,
            description:description,
            dueDate: dueDate? dueDate.toISOString().slice(0,10) : ''
        }
    };
}

export function completeTodo(index) {
    return {
        type: COMPLITE_TODO,
        index
    };
}



export function deleteTodo(index) {
    return {
        type: DELETE_TODO,
        index
    };
}

export function setFilter(filter) {
    return {
        type: SET_FILTER,
        filter
    };
}

export function updateTodo({id, name, description, dueDate}) {
    return {
        type: UPDATE_TODO,
        payload: {
            id,
            name,
            description:description,
            dueDate: dueDate? dueDate.toISOString().slice(0,10) : ''
        }
    };
}

export function freshTodos(todos) {
    return {
        type: FRESH_TODO,
        payload:todos
    };
}
