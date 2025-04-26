import axios from "axios";

import { Story } from "../types/Story";



const API_URL = "http://localhost:8080/api";



export const getAllStories = async (): Promise<Story[]> => {

  const response = await axios.get(`${API_URL}/stories`);

  return response.data;

};



export const getStoriesByDepartment = async (department: string): Promise<Story[]> => {

  const response = await axios.get(`${API_URL}/stories/department/${department}`);

  return response.data;

};



export const getStoryById = async (id: number): Promise<Story> => {

  const response = await axios.get(`${API_URL}/stories/${id}`);

  return response.data;

};



export const createStory = async (story: Story): Promise<Story> => {

  const response = await axios.post(`${API_URL}/stories`, story);

  return response.data;

};



export const updateStory = async (id: number, story: Story): Promise<Story> => {

  const response = await axios.put(`${API_URL}/stories/${id}`, story);

  return response.data;

};



export const deleteStory = async (id: number): Promise<void> => {

  await axios.delete(`${API_URL}/stories/${id}`);

};
